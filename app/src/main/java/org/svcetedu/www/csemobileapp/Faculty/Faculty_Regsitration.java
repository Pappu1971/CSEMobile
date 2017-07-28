package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.R;
import org.svcetedu.www.csemobileapp.StudentRegistration.StudentDash;
import org.svcetedu.www.csemobileapp.StudentRegistration.StudentRegistration;

public class Faculty_Regsitration extends AppCompatActivity {
    private EditText namefield;
    private EditText idfield;
    private EditText emailfield;
    private EditText passfield;
    private Button submit;
    private ProgressDialog mProgress;
    private DatabaseReference facultyDatabase;
    private FirebaseAuth facultyAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__regsitration);
        facultyAuth = FirebaseAuth.getInstance();
        facultyDatabase = FirebaseDatabase.getInstance().getReference().child("FacultyRegistration");


        mProgress = new ProgressDialog(this);
        namefield = (EditText) findViewById(R.id.facultynameField);
        emailfield = (EditText) findViewById(R.id.facultyemailField);
        passfield = (EditText) findViewById(R.id.facultypassField);
        idfield=(EditText)findViewById(R.id.facultyidField);
        submit = (Button) findViewById(R.id.facultysignUp);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });



    }

    private void startRegister() {

        final String name = namefield.getText().toString().trim();
        final String email = emailfield.getText().toString().trim();
        final String id=idfield.getText().toString().trim();
        String pass = passfield.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(id)&&!TextUtils.isEmpty(pass)) ;
        {
            mProgress.setMessage("Registering Your Id...");
            mProgress.show();
            facultyAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String user_id = facultyAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = facultyDatabase.child(user_id);
                        current_user_db.child("facultyname").setValue(name);
                        current_user_db.child("facultyid").setValue(id);
                        current_user_db.child("facultyemail").setValue(email);
                        current_user_db.child("password").setValue("default");
                        mProgress.dismiss();
                        Intent myintent=new Intent(Faculty_Regsitration.this,FacultyDash.class);
                        startActivity(myintent);

                    }

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    }

