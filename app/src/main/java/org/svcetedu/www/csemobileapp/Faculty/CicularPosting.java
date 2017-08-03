package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

        mSelectImage = (ImageButton) findViewById(R.id.imageButton);
        mPostTitle = (EditText) findViewById(R.id.circularTitle);
        mpostDesc = (EditText) findViewById(R.id.circularDesc);
        mSubmit = (Button) findViewById(R.id.submit);


        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }

    private void startPosting() {
        mProgress.setMessage("Sending Your Circular..");
        mProgress.show();
        final String title_val = mPostTitle.getText().toString().trim();
        final String title_desc = mpostDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(title_desc) && imageUri!=null) {
            StorageReference filepath = mStorage.child("Circular_Images").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = mDatabasePost.push();
                    newPost.child("title").setValue(title_val);
                    newPost.child("desc").setValue(title_desc);
                    newPost.child("image").setValue(downloadUrl.toString());


                    mProgress.dismiss();
                    Toast.makeText(CicularPosting.this, "Thank You", Toast.LENGTH_SHORT).show();
                }
            });
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
