package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.svcetedu.www.csemobileapp.R;

import java.io.File;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_computing_lecture);

        notesTtitle=(EditText)findViewById(R.id.notesTitle);
        notesContent=(EditText)findViewById(R.id.notesMain);

        uploadNotes=(ImageButton)findViewById(R.id.uploadnotes);
        uploadNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] ACCEPT_MIME_TYPES = {
                        "application/pdf",
                        "image/*"
                };
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
            }
        });

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
        mProgess.show();
        mProgess.setTitle("Uploading your Data");
        mProgess.setCanceledOnTouchOutside(false);
        mProgess.setMessage("Please wait for a while");
       final String lectureTitle=notesTtitle.getText().toString().trim();
        final String lectureNotes=notesContent.getText().toString().trim();
        if(!TextUtils.isEmpty(lectureTitle) && !TextUtils.isEmpty(lectureNotes) && fileuri!=null)
        {
             StorageReference filepath=mStorage.child("CloudFiles").child(fileuri.getLastPathSegment());
            filepath.putFile(fileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl=taskSnapshot.getDownloadUrl();
                    DatabaseReference cloudComputing=notesDatabase.push();
                    cloudComputing.child("name").setValue(lectureTitle);
                    cloudComputing.child("desc").setValue(lectureNotes);
                    cloudComputing.child("image").setValue(downloadUrl.toString());
                }
            });





        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            fileuri=data.getData();
            uploadNotes.setImageURI(fileuri);
        }
    }
}
