package org.svcetedu.www.csemobileapp.Faculty;

/**
 * Created by pappu on 9/1/17.
 */

public class AllStudentCLass {
    public String name;
    public String roll;
    public String email;

    public AllStudentCLass(String name, String roll, String email) {
        this.name = name;
        this.roll = roll;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AllStudentCLass()
 {

 }

}
