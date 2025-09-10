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
            boolean emailExists = users.stream().anyMatch(u -> u.getEmail().equals(email));
            if (emailExists) {
                throw new EmailAlreadyExistsException("I´m sorry the email already exists!");
            }


            if (password.length() < 8) {
                throw new InvalidPasswordException("I´m sorry : the password must be at least 8 characters.");
            }


            boolean mayus = password.chars().anyMatch(c -> c>= 'A' && c <= 'Z');
            if (!mayus) {
                throw new InvalidPasswordException("I´m sorry :The password must have at least one mayus letter");
            }

            boolean minus = password.chars().anyMatch(c -> c >= 'a' && c <= 'z');
            if (!minus) {
                throw new InvalidPasswordException("I´m sorry The password must have at least one minus letter.");
            }

            boolean number = password.chars().anyMatch(c -> c >= '0' && c <= '9');
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
                    System.out.println("I´m sorry dates incorrect.");
                    return null;
                }
            }
        }
        System.out.println("I´m sorry Email or Password is incorrect.");
        return null;
    }


}
