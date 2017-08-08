package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.svcetedu.www.csemobileapp.R;

public class CicularPosting extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1;
    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mpostDesc;
    private Button mSubmit;
    private Uri imageUri;
    private StorageReference mStorage;
    private DatabaseReference mDatabasePost;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_cicular_posting);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        mDatabasePost = FirebaseDatabase.getInstance().getReference().child("CircularPost");
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

       // mSelectImage = (ImageButton) findViewById(R.id.imageButton);
        mPostTitle = (EditText) findViewById(R.id.circularTitle);
        mpostDesc = (EditText) findViewById(R.id.circularDesc);
        mSubmit = (Button) findViewById(R.id.submit);



        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }

    private void startPosting() {
        String post=mPostTitle.getText().toString().trim();
        String desc=mpostDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(post)&& !TextUtils.isEmpty(desc))
        {
            DatabaseReference postCircular= mDatabasePost.push();
            postCircular.child("CicularTitle").setValue(post);
            postCircular.child("CircularMessage").setValue(desc);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);
        }
    }

}
