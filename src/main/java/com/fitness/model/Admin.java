package com.fitness.model;

public class Admin extends User {
    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
    @Override
    public String toString() {
        return "Admin: " + super.toString();
    }
}
