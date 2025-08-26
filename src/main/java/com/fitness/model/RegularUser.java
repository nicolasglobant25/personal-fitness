package com.fitness.model;

import java.util.ArrayList;
import java.util.List;

public class RegularUser extends User {
    private List<WorkoutLog> logs = new ArrayList<>();

    public RegularUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public List<WorkoutLog> getLogs() {
        return logs;
    }

    public void setLogs(List<WorkoutLog> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "RegularUser: " + super.toString();
    }
}
