package com.testannotation.enrollservice.model;

public class Enroll {
    private String enrollID;

    public String getEnrollID() {
        return enrollID;
    }

    public void setEnrollID(String enrollID) {
        this.enrollID = enrollID;
    }
    private String ID;
    private String duration;

    private String course;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String date) {
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String time) {
        this.course = course;
    }

    public Enroll(String enrollID, String ID, String duration, String course) {
        this.enrollID=enrollID;
        this.ID=ID;
        this.duration=duration;
        this.course=course;

    }
}
