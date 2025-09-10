package com.fitness;

import com.fitness.model.Admin;
import com.fitness.model.User;
import com.fitness.service.*;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        ExerciseService exerciseService = new ExerciseService();
        WorkoutService workoutService = new WorkoutService(exerciseService.getExercises());
        MenuService menuService = new MenuService(userService, exerciseService, workoutService);

        System.out.println("\nWelcome");

        while(true){
            User user = menuService.showLoginRegisterMenu();

            if(user instanceof Admin){
                menuService.showAdminMenu((Admin)user);
            } else {
                menuService.showUserMenu(user);
            }
        }
    }
}
