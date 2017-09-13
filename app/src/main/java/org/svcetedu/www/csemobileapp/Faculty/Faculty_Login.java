package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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

public class Faculty_Login extends AppCompatActivity {
    private EditText emailfaculty;
    private EditText passfaculty;
    private Button login;
    private TextView registeraccount;
    private ProgressDialog mProgress;
    private FirebaseAuth facultyloginAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference facultyloginDatabaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__login);
        mProgress = new ProgressDialog(this);
        facultyloginAuth = FirebaseAuth.getInstance();
        facultyloginDatabaseUser = FirebaseDatabase.getInstance().getReference().child("FacultyRegistration");
        facultyloginDatabaseUser.keepSynced(true);
        emailfaculty = (EditText) findViewById(R.id.facultyloginemail);
        passfaculty = (EditText) findViewById(R.id.facultyloginpass);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(Faculty_Login.this, FacultyDash.class));
                }
            }
        };

        login = (Button) findViewById(R.id.facultylogin);
        registeraccount = (TextView) findViewById(R.id.facultyregister);
        registeraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Faculty_Login.this, Faculty_Regsitration.class);
                startActivity(register);
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
        facultyloginAuth.addAuthStateListener(mAuthListener );

    }

    private void checkLogin() {

        String email = emailfaculty.getText().toString().trim();
        String pass = passfaculty.getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            mProgress.setMessage("Checking Login...");
            mProgress.show();
            facultyloginAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mProgress.dismiss();
                        checkUserExist();
                    } else {

                        mProgress.dismiss();
                        Toast.makeText(Faculty_Login.this, "Error Login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void checkUserExist() {
        final String user_id = facultyloginAuth.getCurrentUser().getUid();
        facultyloginDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(user_id)) {
                    Intent loginIntent = new Intent(Faculty_Login.this, FacultyDash.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(Faculty_Login.this, "You need to setup Your account", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
/*
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }


    }*/

}
