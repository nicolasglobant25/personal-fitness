package com.fitness.model;

public class User {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;

    public User(String firtsName, String lastName, String email, String password) {
        this.firstName = firtsName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }
    public String getFirtsName() {
        return firstName;
    }
    public void setFirtsName(String firtsName) {
        this.firstName = firtsName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + getFirtsName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
