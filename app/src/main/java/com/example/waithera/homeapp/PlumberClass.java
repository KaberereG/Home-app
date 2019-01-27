package com.example.waithera.homeapp;

public class PlumberClass {
    private String firstname,middlename,surname,gender,age,idnumber,citizenship,workernumber,location,experience,previousemployer,referee,duration,charge,username;
    public PlumberClass(){

    }

    public PlumberClass(String name,String middlename,String surname,String gender,String age,String idNumber,String citizenship, String number, String location, String experience, String employer, String referee,String duration,String charge,String username) {
        this.firstname = name;
        this.middlename=middlename;
        this.surname=surname;
        this.gender=gender;
        this.age=age;
        this.idnumber=idNumber;
        this.citizenship=citizenship;
        this.workernumber = number;
        this.location = location;
        this.experience = experience;
        this.previousemployer = employer;
        this.referee=referee;
        this.duration=duration;
        this.charge = charge;
        this.username=username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String fullname) {
        this.firstname = fullname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idnumber;
    }

    public void setIdNumber(String idNumber) {
        this.idnumber = idNumber;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getWorkernumber() {
        return workernumber;
    }

    public void setWorkernumber(String workernumber) {
        this.workernumber = workernumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPreviousemployer() {
        return previousemployer;
    }

    public void setPreviousemployer(String previousemployer) {
        this.previousemployer = previousemployer;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

