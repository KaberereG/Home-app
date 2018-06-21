package com.example.waithera.homeapp;

/**
 * Created by Waithera on 18/05/2018.
 */

public class Worker {
    private String fullname,phoneNumber,location,workExperience,
    prevEmpContact,charges;

    public Worker() {
    }

    public Worker(String fullname, String phoneNumber, String location, String workExperience, String prevEmpContact, String charges) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.workExperience = workExperience;
        this.prevEmpContact = prevEmpContact;
        this.charges = charges;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getPrevEmpContact() {
        return prevEmpContact;
    }

    public String getCharges() {
        return charges;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public void setPrevEmpContact(String prevEmpContact) {
        this.prevEmpContact = prevEmpContact;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
