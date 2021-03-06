package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.Faculty.Result;
import org.svcetedu.www.csemobileapp.R;

public class StudentDash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static long back_pressed;
    private FirebaseUser mCurrentUser;

    private TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final String user_id = getIntent().getStringExtra("name");




        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("StudentRegistration");
        mCurrentUser=FirebaseAuth.getInstance().getCurrentUser();
        mAuth= FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent loginIntent = new Intent(StudentDash.this, Student_Login.class);
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
        Circular fragment = new Circular();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentfame,fragment,"Circulars");
        fragmentTransaction.commit();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_dash, menu);
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
            Intent aboutus=new Intent(StudentDash.this,AboutUs.class);
            startActivity(aboutus);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.logout)
        {
            mAuth.signOut();

        }
        if(id==R.id.circulars)
        {
            setTitle("Cirulars");
            Circular fragment = new Circular();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Circulars");
            fragmentTransaction.commit();
        }
        if(id==R.id.ssyllabus)
        {
            setTitle("Syllabus");
            Syllabus fragment = new Syllabus();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Syllabus");
            fragmentTransaction.commit();
        }

        if(id==R.id.lecturenotes)
        {
            Intent lecuter=new Intent(StudentDash.this,LecutureNotes.class);
            startActivity(lecuter);

        }


        if (id==R.id.onlinelearning)
        {
            Intent onlinelearning=new Intent(StudentDash.this,OnlineCourses.class);
            startActivity(onlinelearning);
        }

        if(id==R.id.results)
        {
            setTitle("Results");
            Result fragment = new Result();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentfame,fragment,"Result");
            fragmentTransaction.commit();
        }


        if(id==R.id.stattendance)
        {
            Intent attendance=new Intent(StudentDash.this,AttendanceView.class);
            startActivity(attendance);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
