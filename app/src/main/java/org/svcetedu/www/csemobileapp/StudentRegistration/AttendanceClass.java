package org.svcetedu.www.csemobileapp.StudentRegistration;

/**
 * Created by pappu on 10/11/17.
 */

public class AttendanceClass {
    public String name;
    public String desc;






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AttendanceClass(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public AttendanceClass()
    {

    }
}
