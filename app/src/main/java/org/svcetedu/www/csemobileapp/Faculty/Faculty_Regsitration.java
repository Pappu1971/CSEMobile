package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.svcetedu.www.csemobileapp.R;

import java.util.HashMap;

public class Faculty_Regsitration extends AppCompatActivity {
    private TextInputLayout namefield;
    private TextInputLayout idfield;
    private TextInputLayout emailfield;
    private TextInputLayout passfield;
    private Button submit;
    private ProgressDialog mProgress;
    private DatabaseReference facultyDatabase;
    private FirebaseAuth facultyAuth;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__regsitration);

        //Toolbar

        mToolbar=(Toolbar)findViewById(R.id.facultyappbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Faculty Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        facultyAuth = FirebaseAuth.getInstance();
        facultyDatabase = FirebaseDatabase.getInstance().getReference().child("FacultyRegistration");


        mProgress = new ProgressDialog(this);
        namefield = (TextInputLayout) findViewById(R.id.facultyname);
        emailfield = (TextInputLayout) findViewById(R.id.faculty_email);
        passfield = (TextInputLayout) findViewById(R.id.faculty_password);
        submit = (Button) findViewById(R.id.reg_create_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = namefield.getEditText().getText().toString();
                String email = emailfield.getEditText().getText().toString();
                String password = passfield.getEditText().getText().toString();

                if(!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mProgress.setTitle("Registering User");
                    mProgress.setMessage("Please wait while we create your account !");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();

                    register_user(display_name, email, password);

                }



            }
        });



    }
    private void register_user(final String display_name, final String email, String password) {

        facultyAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    facultyDatabase = FirebaseDatabase.getInstance().getReference().child("Faculty").child(uid);

                    String device_token = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", display_name);
                    userMap.put("email", email);
                    userMap.put("password", "default");

                    facultyDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                mProgress.dismiss();

                                Intent mainIntent = new Intent(Faculty_Regsitration.this, FacultyDash.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();

                            }

                        }
                    });


                } else {

                    mProgress.hide();
                    Toast.makeText(Faculty_Regsitration.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }

            }
        });

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

