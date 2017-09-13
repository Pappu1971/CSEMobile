package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by pappu on 9/6/17.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {

            case 0:
                CloudComputing chatsFragment = new CloudComputing();
                return  chatsFragment;

            case 1:
                WebServices friendsFragment = new WebServices();
                return friendsFragment;

            case 2:
                CNS cnsfragment=new CNS();
                return cnsfragment;

            case 3: MobileComputing mobileComputing=new MobileComputing();
                return mobileComputing;


            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){

        switch (position) {


            case 0:
                return "CC";

            case 1:
                return "WS";

            case 2:
                return "CNS";
            case 3:
                return "MC";


            default:
                return null;
        }

    }
}
