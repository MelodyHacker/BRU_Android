package com.example.tanon.mybru;

/**
 * Created by MelodyHacker on 10/26/2017.
 */
public class GetSetLocaltion  {
    private String name;
    private String lat;
    private String lng;


    public GetSetLocaltion(String name, String lat, String lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    //////////////////////////////////////////////////////////////////////////
    public String getNameMarker() {
        return name;
    }

    public void setNameMarker(String name) {
        this.name = name;
    }
    ///////////////////////////////////////////////////////////
    public String getlat() {
        return lat;
    }

    public void setlat(String lat) {
        this.lat = lat;
    }
    ///////////////////////////////////////////////////////////
    public String getlng() {
        return lng;
    }

    public void setlng(String lng) {
        this.lng = lng;
    }
    ///////////////////////////////////////////////////////////
}
