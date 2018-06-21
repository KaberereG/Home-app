package com.example.waithera.homeapp;

/**
 * Created by Waithera on 06/06/2018.
 */

public class PlumberClass {
    private String fullname,workernumber,location,experience,previousemployer,charge,username;
    public PlumberClass(){

    }

    public PlumberClass(String name, String number, String location, String experience, String employer, String charge,String username) {
        this.fullname = name;
        this.workernumber = number;
        this.location = location;
        this.experience = experience;
        this.previousemployer = employer;
        this.charge = charge;
        this.username=username;
    }

    public void setName(String name) {
        this.fullname = name;
    }

    public void setNumber(String number) {
        this.workernumber = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setEmployer(String employer) {
        this.previousemployer = employer;
    }

    public void setCharges(String charge) {
        this.charge = charge;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getWorkernumber() {
        return workernumber;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }

    public String getPreviousemployer() {
        return previousemployer;
    }

    public String getCharge() {
        return charge;
    }
    public String getUsername() {
        return username;
    }
}

