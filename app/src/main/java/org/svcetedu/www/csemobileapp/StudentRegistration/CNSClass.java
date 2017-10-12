package org.svcetedu.www.csemobileapp.StudentRegistration;

import android.app.Fragment;

/**
 * Created by pappu on 9/19/17.
 */

public class CNSClass extends Fragment {
    public String title;

    public CNSClass(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }




}
