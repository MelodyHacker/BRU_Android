package com.example.tanon.mybru;


public class GetSetEvent {
    private String event_image1;
    private String event_name;
    private String start_event;
    private String end_event;

    public GetSetEvent(String event_image1, String event_name, String start_event, String end_event) {
        this.event_image1 = event_image1;
        this.event_name = event_name;
        this.start_event = start_event;
        this.end_event = end_event;
    }

    //////////////////////////////////////////////////////////////////////////
    public String getImageEvent1() {
        return event_image1;
    }

    public void setImageEvent1(String event_image1) {
        this.event_image1 = event_image1;
    }

///////////////////////////////////////////////////////////

    public String getNameEvent() {
        return event_name;
    }

    public void setNameEvent(String event_name) {
        this.event_name = event_name;
    }


    //////////////////////////////////////////////////////////////
    public String getStartEvent() {
        return start_event;
    }

    public void setStartEvent(String start_event) {
        this.start_event = start_event;
    }

///////////////////////////////////////////////////////////////////////////////

    public String getEndEvent() {
        return end_event;
    }

    public void setEndEvent(String end_event) {
        this.end_event = end_event;
    }

///////////////////////////////////////////////////////////////////////////////
}
