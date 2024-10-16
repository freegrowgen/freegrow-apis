package com.springUserApi.models;

public class user {
    private String emailId;
    private String firstName;
    private String lastName;
    private long mobileNumber;
    private String image;
    private boolean loggedIn;

    public user() {

    }

    public void setEmailId(String email) {
        this.emailId = email;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setMobileNumber(long no) {
        this.mobileNumber = no;
    }

    public long getMobileNumber() {
        return this.mobileNumber;
    }

    public void setImage(String img) {
        this.image = img;
    }

    public String getImage() {
        return this.image;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

}
