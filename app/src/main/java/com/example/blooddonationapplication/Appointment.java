package com.example.blooddonationapplication;

public class Appointment {
    String id;
    String username;
    String state;
    String centre;
    String date;
    String time;
    String bloodType;

    public Appointment(String id, String username, String state, String centre, String date, String time, String bloodType) {
        this.id = id;
        this.username = username;
        this.state = state;
        this.centre = centre;
        this.date = date;
        this.time = time;
        this.bloodType = bloodType;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {return state; }

    public void setState(String state) {
        this.state = state;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBlood() {
        return bloodType;
    }

    public void setBlood(String bloodType) {
        this.bloodType = bloodType;
    }
}
