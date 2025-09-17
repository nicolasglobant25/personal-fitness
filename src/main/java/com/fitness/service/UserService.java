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
            validatePassword(password);
            users.add(new RegularUser(firstName, lastName, email, password));
            System.out.println("Successfully registered user!!.");
            return true;

        } catch (EmailAlreadyExistsException | InvalidPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void validatePassword(String password) throws InvalidPasswordException {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        if(password == null || !password.matches(regex)) {
            throw new InvalidPasswordException("I'm sorry: The password must be at least 8 characters and contain one uppercase, one lowercase and one number.");
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
