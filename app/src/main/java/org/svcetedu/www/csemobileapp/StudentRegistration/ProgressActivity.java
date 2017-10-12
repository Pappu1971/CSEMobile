package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gospelware.liquidbutton.LiquidButton;

import org.svcetedu.www.csemobileapp.R;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_progress);
        final LiquidButton liquidButton = (LiquidButton) findViewById(R.id.button);
        liquidButton.startPour();
        liquidButton.setEnabled(false);
        liquidButton.setFillAfter(true);
        liquidButton.setAutoPlay(true);
        liquidButton.finishPour();


        liquidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        liquidButton.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                Toast.makeText(ProgressActivity.this, "Please Login or Signup", Toast.LENGTH_SHORT).show();
                Intent scroll=new Intent(ProgressActivity.this,Student_Login.class);
                startActivity(scroll);

            }

            @Override
            public void onProgressUpdate(float progress) {
            }
        });
    }
}
