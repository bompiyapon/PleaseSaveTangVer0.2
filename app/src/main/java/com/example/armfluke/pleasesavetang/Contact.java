package com.example.armfluke.pleasesavetang;

public class Contact {

    //private variables
    String username;
    String password;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(String username, String password){
        this.username = username;
        this.password = password;
    }

    // getting username
    public String getUsername(){
        return this.username;
    }

    // setting username
    public void setUsername(String username){
        this.username = username;
    }

    // getting password
    public String getPassword(){
        return this.password;
    }

    // setting phone number
    public void setPassword(String password){
            this.password = password;
    }
}