package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import org.svcetedu.www.csemobileapp.R;

/**
 * Created by pappu on 9/8/17.
 */

public class Intro extends AppIntro{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome", "Null",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(), R.color.slide1)));
        addSlide(AppIntroFragment.newInstance("Work Hard", "Code Everyday with your New Idea",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(),R.color.slide2)));
        addSlide(AppIntroFragment.newInstance("Open your Market", "Upload Your App In PlayStore",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(),R.color.slide3)));
        addSlide(AppIntroFragment.newInstance("Make App Viral", "Support in any Platform",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(),R.color.slide4)));
        addSlide(AppIntroFragment.newInstance("Keep Always Update", "Build With Updated API",
                R.drawable.logoname, ContextCompat.getColor(getApplicationContext(),R.color.slide5)));


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
        finish();
        // Do something when users tap on Done button.
    }
}
