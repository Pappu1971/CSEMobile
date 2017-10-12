package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.R;

public class CNSLecturePosting extends AppCompatActivity {
    private EditText cnsTitle;
    private EditText cnsDesc;
    private Button cnsSubmitNOtes;
    private DatabaseReference cnsLecture;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnslecture_posting);

        //Database Declaration

        cnsLecture=FirebaseDatabase.getInstance().getReference().child("CnsLecture");

        mProgress=new ProgressDialog(this);

        cnsTitle=(EditText)findViewById(R.id.cnsnotesTitle);
        cnsDesc=(EditText)findViewById(R.id.cnsnotesLink);
        cnsSubmitNOtes=(Button)findViewById(R.id.cnsnotesSubmit);


        cnsSubmitNOtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitNotes();
            }
        });
    }

    private void submitNotes() {
        mProgress.setMessage("We are uploading your CNS notes");
        mProgress.setTitle("Please wait");
        mProgress.show();
        String title=cnsTitle.getText().toString().trim();
        String desc=cnsDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(title)&& !TextUtils.isEmpty(desc))
        {
            DatabaseReference cnsDatabase=cnsLecture.push();
            cnsDatabase.child("title").setValue(title);
            cnsDatabase.child("desc").setValue(desc);

            mProgress.dismiss();

        }
        


    }
}
