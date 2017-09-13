package org.svcetedu.www.csemobileapp.Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.svcetedu.www.csemobileapp.R;

public class AllStudent extends AppCompatActivity {
    private TextView studentName;
    private TextView rollnumber;
    private TextView department;
    private DatabaseReference mDatabaseStudent;
    private RecyclerView allStudent;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);
        //toolbar

        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Registered Students");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView icon=new ImageView(this);
        icon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_add_circle_outline_black_24dp));

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();



        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        //Button 1
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_gallery));
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

//Button 2
       itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_back_white));
        SubActionButton button2 = itemBuilder.setContentView(itemIcon).build();


//Button 3
      itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_camera));
        SubActionButton button3 = itemBuilder.setContentView(itemIcon).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cloudComputing=new Intent(AllStudent.this,CloudComputingLecturePost.class);
                startActivity(cloudComputing);
            }
        });

//Button 4
     itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_share));
        SubActionButton button4 = itemBuilder.setContentView(itemIcon).build();



        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                // ...
                .attachTo(actionButton)
                .build();

        mDatabaseStudent= FirebaseDatabase.getInstance().getReference().child("StudentRegistration");
        studentName=(TextView)findViewById(R.id.studentname);
        rollnumber=(TextView)findViewById(R.id.rollnumber);
        department=(TextView)findViewById(R.id.department);

        allStudent=(RecyclerView)findViewById(R.id.allstudentRecylce);
        allStudent.setHasFixedSize(true);
        allStudent.setLayoutManager(new LinearLayoutManager(this));





    }

    @Override
    protected void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<AllStudentCLass, AllStudent.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AllStudentCLass, AllStudent.BlogViewHolder>(
                AllStudentCLass.class, R.layout.all_user_single_blog, AllStudent.BlogViewHolder.class, mDatabaseStudent
        ) {
            @Override
            protected void populateViewHolder(AllStudent.BlogViewHolder viewHolder, AllStudentCLass model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setStudentName(model.getName());

                viewHolder.setRollName(model.getRoll());

                viewHolder.setEmail(model.getEmail());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent singleblog = new Intent(CircularDisplay.this, BlogSingleActivity.class);
                        // singleblog.putExtra("blog_id", post_key);
                        // startActivity(singleblog);
                        //overridePendingTransition(R.anim.bounce,R.anim.bounce);
                    }
                });


            }
        };
        allStudent.setAdapter(firebaseRecyclerAdapter);


    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

    public void setStudentName(String name) {
        TextView studentName = (TextView)mView.findViewById(R.id.studentname);
        studentName.setText(name);
        }

        public void setRollName(String roll)
        {
            TextView rollNumberset=(TextView)mView.findViewById(R.id.rollnumber);
            rollNumberset.setText(roll);
        }

        public void setEmail(String email)
        {
            TextView departmentName=(TextView)mView.findViewById(R.id.department);
            departmentName.setText(email);
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
