package com.fitness;


import com.fitness.service.*;
import com.fitness.view.Menu;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        ExerciseService exerciseService = new ExerciseService();
        WorkoutService workoutService = new WorkoutService(exerciseService.getExercises());
        Menu menu = new Menu(userService,exerciseService,workoutService);

        menu.startMenu();


    }
}
