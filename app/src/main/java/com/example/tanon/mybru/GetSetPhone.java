package com.example.tanon.mybru;

/**
 * Created by MelodyHacker on 11/1/2017.
 */

public class GetSetPhone {
    private String name;
    private String phone;
    private String image;


    public GetSetPhone(String name, String phone, String image) {
        this.name = name;
        this.phone = phone;
        this.image = image;

    }

    //////////////////////////////////////////////////////////////////////////
    public String getNamePhone() {
        return name;
    }

    public void setNamePhone(String name) {
        this.name = name;
    }

    ///////////////////////////////////////////////////////////
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    ///////////////////////////////////////////////////////////
    public String getImagePhone() {
        return image;
    }

    public void setImagePhone(String image) {
        this.image = image;
    }
}