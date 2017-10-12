package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import org.svcetedu.www.csemobileapp.R;

/**
 * Created by pappu on 9/8/17.
 */

public class Intro extends AppIntro{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome", "Version 1.1",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(), R.color.slide1)));
        addSlide(AppIntroFragment.newInstance("Circular", "Get important Circulars",
                R.drawable.circular, ContextCompat.getColor(getApplicationContext(),R.color.slide2)));
        addSlide(AppIntroFragment.newInstance("Daily Notes", "Update with your daily notes",
                R.drawable.note, ContextCompat.getColor(getApplicationContext(),R.color.slide3)));
        addSlide(AppIntroFragment.newInstance("Secure Upload", "Easy and Secure",
                R.drawable.upload, ContextCompat.getColor(getApplicationContext(),R.color.slide4)));


    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent progressIntent=new Intent(Intro.this,ProgressActivity.class);
        startActivity(progressIntent);
        finish();
        // Do something when users tap on Done button.
    }
}
