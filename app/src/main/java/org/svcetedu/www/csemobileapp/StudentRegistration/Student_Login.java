package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.svcetedu.www.csemobileapp.R;

public class Student_Login extends AppCompatActivity {

    private static long back_pressed;
    private EditText emailstudent;
    private EditText passstudent;
    private Button login;
    private TextView registeraccount;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student__login);
        //App Intro

        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Intent newactivity=new Intent(getApplicationContext(),Intro.class);
            startActivity(newactivity);
            Log.d("Comments", "First time");

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        else {


        }
















        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("StudentRegistration");
        mDatabaseUser.keepSynced(true);
        emailstudent = (EditText) findViewById(R.id.studentloginemail);
        passstudent = (EditText) findViewById(R.id.studentloginpass);
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(Student_Login.this, StudentDash.class));
                }
            }
        };

        login = (Button) findViewById(R.id.login);
        registeraccount = (TextView) findViewById(R.id.register);
        registeraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Student_Login.this, StudentRegistration.class);
                startActivity(register);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

    }
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener );

    }

    private void checkLogin() {

        String email = emailstudent.getText().toString().trim();
        String pass = passstudent.getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            mProgress.setMessage("Checking Login...");
            mProgress.show();
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mProgress.dismiss();
                        checkUserExist();
                    } else {

                        mProgress.dismiss();
                        Toast.makeText(Student_Login.this, "Error Login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void checkUserExist() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(user_id)) {
                    Intent loginIntent = new Intent(Student_Login.this, StudentDash.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(loginIntent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } else {
                    Toast.makeText(Student_Login.this, "You need to setup Your account", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();

        }


    }
    }

