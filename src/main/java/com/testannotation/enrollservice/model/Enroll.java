package com.testannotation.enrollservice.model;

public class Enroll {
    private String enrollID;
    private String studentID;
    private String duration;
    private String course;

    public String getEnrollID() {
        return enrollID;
    }

    public void setEnrollID(String enrollID) {
        this.enrollID = enrollID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public Enroll() {}

    public Enroll(String enrollID, String studentID, String duration, String course) {
        this.enrollID=enrollID;
        this.studentID = studentID;
        this.duration=duration;
        this.course=course;
    }

    public Enroll(String studentID, String duration, String course) {
        this.studentID = studentID;
        this.duration=duration;
        this.course=course;
    }
}
