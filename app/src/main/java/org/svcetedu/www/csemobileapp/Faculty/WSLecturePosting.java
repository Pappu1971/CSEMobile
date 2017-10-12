package org.svcetedu.www.csemobileapp.Faculty;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.R;

public class WSLecturePosting extends AppCompatActivity {

    private EditText wsLectureTitle;
    private EditText wsLectureDesc;
    private Button postYourNotes;
    private DatabaseReference wsDatabase;
    private ProgressDialog mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wslecture_posting);
        wsDatabase= FirebaseDatabase.getInstance().getReference().child("WSNotes");

        //Progressbar

        mProgress=new ProgressDialog(this);


        wsLectureTitle=(EditText)findViewById(R.id.wsnotesTitle);
        wsLectureDesc=(EditText)findViewById(R.id.wsnotesLink);
        postYourNotes=(Button)findViewById(R.id.wsnotesSubmit);
        postYourNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPostYourNotes();
            }
        });

    }

    private void startPostYourNotes() {
        mProgress.setMessage("Sir We are Uploading your Notes");
        mProgress.setTitle("Please Wait ");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        String wsTitle=wsLectureTitle.getText().toString().trim();
        String wsDesc=wsLectureDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(wsTitle) && !TextUtils.isEmpty(wsDesc))
        {
            DatabaseReference ws=wsDatabase.push();
            ws.child("title").setValue(wsTitle);
            ws.child("desc").setValue(wsDesc);
            Toast.makeText(this, "Thank You Sir", Toast.LENGTH_SHORT).show();
           mProgress.dismiss();



        }





    }
}
