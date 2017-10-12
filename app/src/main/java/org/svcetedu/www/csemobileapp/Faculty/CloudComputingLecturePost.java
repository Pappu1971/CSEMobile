package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.svcetedu.www.csemobileapp.R;
import org.svcetedu.www.csemobileapp.StudentRegistration.LecutureNotes;

public class CloudComputingLecturePost extends AppCompatActivity {
    private EditText notesTtitle;
    private EditText notesContent;
    private ImageButton uploadNotes;

    private Uri fileuri;

    private StorageReference mStorage;

    private static final int GALLERY_REQUEST=1;

    private Button submitNOtes;
    private DatabaseReference notesDatabase;
    private ProgressDialog mProgess;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_computing_lecture);

        notesTtitle=(EditText)findViewById(R.id.notesTitle);
        notesContent=(EditText)findViewById(R.id.notesMain);


        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Cloud Computing Lecture");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Progress Dialog

        mProgess=new ProgressDialog(this);
        notesDatabase= FirebaseDatabase.getInstance().getReference().child("CloudComputingNotes");
        mStorage= FirebaseStorage.getInstance().getReference();
        submitNOtes=(Button)findViewById(R.id.notesSubmit);

        submitNOtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesSubmit();
            }
        });
    }

    private void notesSubmit() {
        mProgess.setTitle("Uploading your Data");
        mProgess.setCanceledOnTouchOutside(false);
        mProgess.setMessage("Please wait for a while");
        mProgess.show();
       final String lectureTitle=notesTtitle.getText().toString().trim();
        final String lectureNotes=notesContent.getText().toString().trim();
        if(!TextUtils.isEmpty(lectureTitle) && !TextUtils.isEmpty(lectureNotes))
        {
                    DatabaseReference cloudComputing=notesDatabase.push();
                    cloudComputing.child("name").setValue(lectureTitle);
                    cloudComputing.child("desc").setValue(lectureNotes);


            mProgess.dismiss();
            Intent start=new Intent(CloudComputingLecturePost.this,FacultyDash.class);
            startActivity(start);

                }

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ccmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.notesview) {
            Intent ccnotesview=new Intent(CloudComputingLecturePost.this, LecutureNotes.class);
            startActivity(ccnotesview);
        }

        return super.onOptionsItemSelected(item);
    }
    }


