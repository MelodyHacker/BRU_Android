package com.example.tanon.mybru;

/**
 * Created by MelodyHacker on 10/29/2017.
 */

public class GetSetImageMain {

    private String image1, image2, image3;


    public GetSetImageMain(String image1, String image2, String image3) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;

    }

    //////////////////////////////////////////////////////////////////////////
    public String getImageEvent1() {
        return image1;
    }

    public void setImageEvent1(String image1) {
        this.image1 = image1;
    }

    ///////////////////////////////////////////////////////////
    public String getImageEvent2() {
        return image2;
    }

    public void setImageEvent2(String image2) {
        this.image2 = image2;
    }

    ///////////////////////////////////////////////////////////
    public String getImageEvent3() {
        return image3;
    }

    public void setImageEvent3(String image3) {
        this.image3 = image3;
    }

///////////////////////////////////////////////////////////


}
