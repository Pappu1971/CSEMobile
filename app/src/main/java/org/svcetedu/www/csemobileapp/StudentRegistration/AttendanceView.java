package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.R;

public class AttendanceView extends AppCompatActivity {

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
        setContentView(R.layout.activity_attendance_view);
        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mDatabaseStudent= FirebaseDatabase.getInstance().getReference().child("CloudComputing");
        studentName=(TextView)findViewById(R.id.percentagefield);
        rollnumber=(TextView)findViewById(R.id.rollNumberfield);


        //department=(TextView)findViewById(R.id.department);

        allStudent=(RecyclerView)findViewById(R.id.attendanceview);
        allStudent.setHasFixedSize(true);
        allStudent.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<AttendanceClass, AttendanceView.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AttendanceClass, AttendanceView.BlogViewHolder>(
                AttendanceClass.class, R.layout.attendancerow, AttendanceView.BlogViewHolder.class, mDatabaseStudent
        ) {
            @Override
            protected void populateViewHolder(AttendanceView.BlogViewHolder viewHolder, AttendanceClass model, int position) {


                viewHolder.setTitle(model.getName());
                viewHolder.setDesc(model.getDesc());



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

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.rollNumberfield);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.percentagefield);
            post_desc.setText(desc);
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
