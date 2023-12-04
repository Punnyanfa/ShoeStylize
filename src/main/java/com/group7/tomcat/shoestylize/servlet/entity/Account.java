package com.group7.tomcat.shoestylize.servlet.entity;

import java.util.Date;

public class Account {

    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private Date dob;
    private String phonenumber;
    private String city;
    private String state;
    private String role;
    private String loginMethod;
    private String token;

    public Account() {
    }

    public Account(int id, String username, String firstname, String lastname, String email, String password, String address, Date dob, String phonenumber, String city, String state, String role, String loginMethod, String token) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dob = dob;
        this.phonenumber = phonenumber;
        this.city = city;
        this.state = state;
        this.role = role;
        this.loginMethod = loginMethod;
        this.token = token;
    }


    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public String getLoginMethod() {
        return loginMethod;
    }


}
