package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by pappu on 9/12/17.
 */

public class OnlineCoursesAdapter extends FragmentPagerAdapter{
    public OnlineCoursesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {

            case 0:
                Stackoverflow stackoverflow = new Stackoverflow();
                return  stackoverflow;

            case 1:
                TutorialPoint tutorialPoint = new TutorialPoint();
                return tutorialPoint;

            case 2:
                Quora quora=new Quora();
                return quora;


            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){

        switch (position) {


            case 0:
                return "Stack Overflow";

            case 1:
                return "Tutorials Point";
            case 2:
                return  "Quora";




            default:
                return null;
        }

    }
}
