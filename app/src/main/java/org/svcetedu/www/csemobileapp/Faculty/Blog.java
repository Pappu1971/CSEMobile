package org.svcetedu.www.csemobileapp.Faculty;

/**
 * Created by pappu on 7/31/17.
 */

public class Blog {

    public Blog(){

    }


    public Blog(String image, String desc, String title) {
        this.image = image;
        this.desc = desc;
        this.title = title;
        //this.name=name;
    }
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String title;
    private String desc;
    private String image;

}
