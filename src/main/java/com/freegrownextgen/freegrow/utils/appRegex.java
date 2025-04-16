package com.freegrownextgen.freegrow.utils;

public class appRegex {
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String mobileNumberRegex = "^\\+?[0-9]{8,15}$";

    public boolean isValidEmail(String email) {
        return email.matches(emailRegex);
    }

    public boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches(mobileNumberRegex);
    }

}
