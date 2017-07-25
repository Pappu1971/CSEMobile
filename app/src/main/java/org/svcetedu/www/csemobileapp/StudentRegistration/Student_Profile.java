package org.svcetedu.www.csemobileapp.StudentRegistration;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.svcetedu.www.csemobileapp.R;

public class Student_Profile extends AppCompatActivity {
    private ImageButton mSetupImageBtn;
    private EditText mNamefield;
    private EditText mRollfield;
    private Button mSubmitbtn;
    private Uri mImageUri=null;
    private FirebaseAuth mSetupAuth;
    private StorageReference mStorageImage;
    private static final int GALLERY_REQUEST=1;
    private DatabaseReference mDatabaseUsers;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile);
        mSetupAuth=FirebaseAuth.getInstance();
        mProgress=new ProgressDialog(this);
        mStorageImage= FirebaseStorage.getInstance().getReference().child("Profile_images");
        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("User");
        mSetupImageBtn=(ImageButton)findViewById(R.id.setupImage);
        mNamefield=(EditText)findViewById(R.id.setupNameField);
        mRollfield=(EditText)findViewById(R.id.setupRollField);



        mSubmitbtn=(Button)findViewById(R.id.setup);

        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSetupAccount();
            }
        });


        mSetupImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });
    }
    private void startSetupAccount() {
        mProgress.setMessage("Finalizing Your Profile.....");
        mProgress.show();
        final String name=mNamefield.getText().toString().trim();
        final String roll=mRollfield.getText().toString().trim();
        final String user_id=mSetupAuth.getCurrentUser().getUid();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(roll) && mImageUri !=null)
        {
            StorageReference filepath=mStorageImage.child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String downloadUri=taskSnapshot.getDownloadUrl().toString();
                    mDatabaseUsers.child(user_id).child("Name").setValue(name);
                    mDatabaseUsers.child(user_id).child("Roll").setValue(roll);
                    mDatabaseUsers.child(user_id).child("image").setValue(downloadUri);

                    mProgress.dismiss();
                    //startActivity(new Intent(SetupActivity.this,LatestHome.class));
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            Uri imageUri=data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mImageUri = result.getUri();
                mSetupImageBtn.setImageURI(mImageUri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
