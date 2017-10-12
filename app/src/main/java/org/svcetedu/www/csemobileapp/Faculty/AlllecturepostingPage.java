package org.svcetedu.www.csemobileapp.Faculty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.svcetedu.www.csemobileapp.R;

public class AlllecturepostingPage extends AppCompatActivity {
    private ImageButton cc;
    private ImageButton ws;
    private ImageButton ai;
    private ImageButton mc;
    private ImageButton acn;
    private ImageButton st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllectureposting_page);

        cc=(ImageButton)findViewById(R.id.cc);
        ws=(ImageButton)findViewById(R.id.ws);
        ai=(ImageButton)findViewById(R.id.ai);
        mc=(ImageButton)findViewById(R.id.mc);
        acn=(ImageButton)findViewById(R.id.cns);
        st=(ImageButton)findViewById(R.id.st);


        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AlllecturepostingPage.this, "CC", Toast.LENGTH_SHORT).show();
            }
        });

        ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        acn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
