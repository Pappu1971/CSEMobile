package org.svcetedu.www.csemobileapp.Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.svcetedu.www.csemobileapp.R;
import org.svcetedu.www.csemobileapp.StudentRegistration.AboutUs;
import org.svcetedu.www.csemobileapp.StudentRegistration.Syllabus;

public class FacultyDash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static long back_pressed;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Firebase Works
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
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.note));
        SubActionButton button2 = itemBuilder.setContentView(itemIcon).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postCC=new Intent(FacultyDash.this,CloudComputingLecturePost.class);
                startActivity(postCC);
            }
        });


//Button 3
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.circular));
        SubActionButton button3 = itemBuilder.setContentView(itemIcon).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postCircular=new Intent(FacultyDash.this,CicularPosting.class);
                startActivity(postCircular);
            }
        });

//Button 4
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_share));
        SubActionButton button4 = itemBuilder.setContentView(itemIcon).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FacultyDash.this, "Thank You ", Toast.LENGTH_SHORT).show();
            }
        });



        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                // ...
                .attachTo(actionButton)
                .build();


        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("Faculty");
        mCurrentUser=FirebaseAuth.getInstance().getCurrentUser();
        mAuth= FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent loginIntent = new Intent(FacultyDash.this, Faculty_Login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
            }
        };


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        setTitle("Circulars");
        FacultyCircularDisplay fragment = new FacultyCircularDisplay();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentfame,fragment,"Circulars");
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
        else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faculty_dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent aboutus=new Intent(FacultyDash.this, AboutUs.class);
            startActivity(aboutus);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.facultycirculars)
        {
            setTitle("Circulars");
            FacultyCircularDisplay fragment = new FacultyCircularDisplay();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Circulars");
            fragmentTransaction.commit();
        }


        if (id==R.id.postCircular)
        {
            Intent startPosting= new Intent(FacultyDash.this,CicularPosting.class);
            startActivity(startPosting);
        }

        if(id==R.id.studentlist)
        {
            Intent studentList=new Intent(FacultyDash.this,AllStudent.class);
            startActivity(studentList);
        }

        if(id==R.id.lecCloudComputing)
        {
            Intent lectureCloudComputing=new Intent(FacultyDash.this,CloudComputingLecturePost.class);
            startActivity(lectureCloudComputing);
        }

        if (id==R.id.fsyllabus)
        {
            setTitle("Syllabus");
            Syllabus fragment = new Syllabus();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Syllabus");
            fragmentTransaction.commit();
        }

        if(id==R.id.logoutFaculty)
        {
            mAuth.signOut();

        }


        if(id==R.id.fresults)
        {
            setTitle("Results");
            Result fragment = new Result();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Result");
            fragmentTransaction.commit();
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
