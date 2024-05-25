package com.example.eventprojectforcomputing;

public class MainModelLayan1 {
    String available,evaluation,location,eventname,picture;

    MainModelLayan1(){

    }

    public MainModelLayan1(String available, String evaluation, String location, String eventname, String picture) {
        this.available = available;
        this.evaluation = evaluation;
        this.location = location;
        this.eventname = eventname;
        this.picture = picture;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
