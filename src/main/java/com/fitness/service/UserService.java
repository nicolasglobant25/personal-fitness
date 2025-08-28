package com.fitness.service;

import com.fitness.exceptions.EmailAlreadyExistsException;
import com.fitness.exceptions.InvalidPasswordException;
import com.fitness.model.RegularUser;
import com.fitness.model.User;
import com.fitness.model.Admin;


import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new Admin("Nicolas", "Mora", "nicolasmora@gmail.com", "Nicolas1234"));
    }

    public boolean register(String firstName, String lastName, String email, String password) {
        try {
            for (User u : users) {
                if (u.getEmail().equals(email)) {
                    throw new EmailAlreadyExistsException("Error: El email ya existe.");
                }
            }

            if (password.length() < 8) {
                throw new InvalidPasswordException("Error: La contraseña debe tener al menos 8 caracteres.");
            }

            boolean mayus = false;
            for (char c : password.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    mayus = true;
                }
            }
            if (!mayus) {
                throw new InvalidPasswordException("I´m sorry :The password must have at least one mayus letter");
            }

            boolean minus = false;
            for (char c : password.toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    minus = true;
                }
            }
            if (!minus) {
                throw new InvalidPasswordException("I´m sorry The password must have at least one minus letter.");
            }

            boolean number= false;
            for (char c : password.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    number = true;
                }
            }
            if (!number) {
                throw new InvalidPasswordException("I´m sorry :The password must have at least one number.");
            }

            users.add(new RegularUser(firstName, lastName, email, password));
            System.out.println("Successfully registered user!!.");
            return true;

        } catch (EmailAlreadyExistsException | InvalidPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                if (u.getPassword().equals(password)) {
                    System.out.println("successful login!!!.");
                    return u;
                } else {
                    System.out.println("I´m sorry password incorrect.");
                    return null;
                }
            }
        }
        System.out.println("I´m sorry password incorrect.");
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
