package com.fitness.service;

import com.fitness.model.Exercise;
import com.fitness.model.User;
import com.fitness.model.Admin;
import java.util.ArrayList;
import java.util.List;

public class ExerciseService {

    private List<Exercise> exerciseList = new ArrayList<>();

    public ExerciseService() {
        defaultExercise();
    }

    public void defaultExercise() {
        exerciseList.add(new Exercise("Running", 0, 0));
        exerciseList.add(new Exercise("Cycling", 0, 0));
        exerciseList.add(new Exercise("Swimming", 0, 0));
        exerciseList.add(new Exercise("Jump Rope", 0, 0));
        exerciseList.add(new Exercise("Push-Ups", 10, 3));
        exerciseList.add(new Exercise("Squats", 15, 3));
    }

    public void addExercise(Exercise exercise, User user) {
        if (user instanceof Admin) {
            exerciseList.add(exercise);
            System.out.println("Exercise added correctly: " + exercise);
        } else {
            System.out.println("Only admins can add exercises");
        }
    }

    public void removeExercise(Exercise exercise, User user) {
        if (user instanceof Admin) {
            if (exerciseList.contains(exercise)) {
                exerciseList.remove(exercise);
                System.out.println("Exercise removed correctly: " + exercise);
            } else {
                System.out.println("The exercise does not exist in the list.");
            }
        } else {
            System.out.println("Access denied. Only administrators can remove exercises.");
        }
    }

    public void listExercises() {
        if (exerciseList.isEmpty()) {
            System.out.println("No exercises registered.");
        } else {
            System.out.println("Exercise List:");
            for (Exercise exercise : exerciseList) {
                System.out.println(exercise);
            }
        }
    }

    public List<Exercise> getExercises() {
        return exerciseList;
    }
}