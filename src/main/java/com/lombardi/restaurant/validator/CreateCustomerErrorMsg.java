package com.lombardi.restaurant.validator;

public class CreateCustomerErrorMsg {

    private String firstNameErr;
    private String lastNameErr;
    private String emailErr;
    private String passwordErr;

    public CreateCustomerErrorMsg() {
        this.firstNameErr = "";
        this.lastNameErr = "";
        this.emailErr = "";
        this.passwordErr = "";
    }

    public String getFirstNameErr() {
        return firstNameErr;
    }

    public void setFirstNameErr(String firstNameErr) {
        this.firstNameErr = firstNameErr;
    }

    public String getLastNameErr() {
        return lastNameErr;
    }

    public void setLastNameErr(String lastNameErr) {
        this.lastNameErr = lastNameErr;
    }

    public String getEmailErr() {
        return emailErr;
    }

    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    @Override
    public String toString() {
        return "CreateCustomerErrorMsg{" +
                "firstNameErr='" + firstNameErr + '\'' +
                ", lastNameErr='" + lastNameErr + '\'' +
                ", emailErr='" + emailErr + '\'' +
                ", passwordErr='" + passwordErr + '\'' +
                '}';
    }
}
