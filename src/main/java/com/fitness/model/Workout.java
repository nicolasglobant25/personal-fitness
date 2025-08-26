package com.fitness.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private String description;
    private List<Exercise> exercises = new ArrayList<>();

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Exercise> getExercises() {
        return exercises;
    }
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Workout: " + name + " - " + description + " | Exercises: " + exercises;
    }
}
