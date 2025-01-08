package com.example.travelguide;

public class HelperClass {
    String email, password, contact, userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public HelperClass(String email, String password, String contact, String userName) {
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.userName = userName;
    }

    public HelperClass() {
    }


}
