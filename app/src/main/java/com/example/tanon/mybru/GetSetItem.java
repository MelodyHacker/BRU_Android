package com.example.tanon.mybru;


public class GetSetItem {
    private String event_image1;
    private String event_image2;
    private String event_image3;
    private String event_name;
    private String event_description;
    private String start_event;
    private String end_event;
    private String lat_event;
    private String long_event;

    public GetSetItem(String event_image1, String event_image2, String event_image3, String event_name, String event_description, String start_event, String end_event, String lat_event, String long_event) {
        this.event_image1 = event_image1;
        this.event_image2 = event_image2;
        this.event_image3 = event_image3;
        this.event_name = event_name;
        this.event_description = event_description;
        this.start_event = start_event;
        this.end_event = end_event;
        this.lat_event = lat_event;
        this.long_event = long_event;
    }

    //////////////////////////////////////////////////////////////////////////
    public String getImageEvent1Item() {
        return event_image1;
    }

    public void setImageEvent1Item(String event_image1) {
        this.event_image1 = event_image1;
    }
/////////////////////////////////////////////////////////

    public String getImageEvent2Item() {
        return event_image2;
    }

    public void setImageEvent2Item(String event_image2) {
        this.event_image2 = event_image2;
    }

//////////////////////////////////////////////////////////

    public String getImageEvent3Item() {
        return event_image3;
    }

    public void setImageEvent3Item(String event_image3) {
        this.event_image3 = event_image3;
    }
///////////////////////////////////////////////////////////

    public String getNameEventItem() {
        return event_name;
    }

    public void setNameEvent(String event_name) {
        this.event_name = event_name;
    }

//////////////////////////////////////////////////////////

    public String getDescriptionEventItem() {
        return event_description;
    }

    public void setDescriptionEventItem(String event_description) {
        this.event_description = event_description;
    }

    //////////////////////////////////////////////////////////////
    public String getStartEventItem() {
        return start_event;
    }

    public void setStartEventItem(String start_event) {
        this.start_event = start_event;
    }

///////////////////////////////////////////////////////////////////////////////

    public String getEndEventItem() {
        return end_event;
    }

    public void setEndEventItem(String end_event) {
        this.end_event = end_event;
    }
    //////////////////////////////////////////////////////////////////////////
    public String getLat_event() {
        return lat_event;
    }

    public void setLat_event(String lat_event) {
        this.lat_event = lat_event;
    }
    //////////////////////////////////////////////////////////////////////////
    public String getLong_event() {
        return long_event;
    }

    public void setLong_event(String long_event) {
        this.long_event = long_event;
    }
/////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
}
