package com.fitness;

import com.fitness.model.User;
import com.fitness.service.UserService;
import com.fitness.service.ExerciseService;
import com.fitness.service.WorkoutService;
import com.fitness.model.Exercise;
import com.fitness.model.Workout;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        ExerciseService exerciseService = new ExerciseService();
        WorkoutService workoutService = new WorkoutService(exerciseService.getExercises());

        User user = null;

        System.out.println("\nWelcome");
        while (true) {

            System.out.println("\n1. Register\n2. Log in");
            String op = sc.nextLine();
            if (op.equals("1")) {
                System.out.print("First Name: ");
                String n = sc.nextLine();
                System.out.print("Last Name: ");
                String a = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                userService.register(n, a, email, pass);
                System.out.println("Registered, please log in");
            } else if (op.equals("2")) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                user = userService.login(email, pass);
                if (user != null) System.out.println("Correct!");

            }

            if (user != null) {
                boolean exit = false;
                while (!exit) {
                    if (user instanceof com.fitness.model.Admin) {
                        System.out.println("\n1. View workouts\n2. Add exercise\n3. Remove exercise\n4. Add workout\n5. Remove workout\n0. Exit");
                        String opc = sc.nextLine();
                        switch (opc) {
                            case "1":
                                workoutService.listWorkouts();
                                break;
                            case "2":
                                System.out.print("Exercise name: ");
                                String en = sc.nextLine();
                                System.out.print("Reps: ");
                                int r = Integer.parseInt(sc.nextLine());
                                System.out.print("Sets: ");
                                int s = Integer.parseInt(sc.nextLine());
                                exerciseService.addExercise(new Exercise(en, r, s), user);
                                break;
                            case "3":
                                exerciseService.listExercises();
                                System.out.print("Enter the name of the exercise to remove: ");
                                String rname = sc.nextLine();
                                Exercise delEx = null;
                                for (Exercise e : exerciseService.getExercises()) {
                                    if (e.getName().equalsIgnoreCase(rname)) {
                                        delEx = e;
                                        break;
                                    }
                                }
                                if (delEx != null) exerciseService.removeExercise(delEx, user);
                                break;
                            case "4":
                                System.out.print("Name: ");
                                String wn = sc.nextLine();
                                System.out.print("Description: ");
                                String desc = sc.nextLine();
                                Workout w = new Workout(wn, desc);
                                System.out.println("Add exercises:");
                                exerciseService.listExercises();
                                String line = sc.nextLine();
                                for (String exName : line.split(",")) {
                                    for (Exercise e : exerciseService.getExercises()) {
                                        if (e.getName().equalsIgnoreCase(exName.trim())) {
                                            w.getExercises().add(e);
                                        }
                                    }
                                }
                                workoutService.addWorkout(w, user);
                                break;
                            case "5":
                                workoutService.listWorkouts();
                                System.out.print("Remove workout #: ");
                                int remIdx = Integer.parseInt(sc.nextLine()) - 1;
                                if (remIdx >= 0 && remIdx < workoutService.getWorkouts().size()) {
                                    Workout remWorkout = workoutService.getWorkouts().get(remIdx);
                                    workoutService.removeWorkout(remWorkout, user);
                                    System.out.println("Workout removed: " + remWorkout.getName() + "!");
                                } else {
                                    System.out.println("Invalid number.");
                                }
                                break;
                            case "0":
                                exit = true;
                                break;
                        }
                    } else {
                        // User
                        System.out.println("\n1. View workouts\n2. Register workout\n3. View logs\n0. Exit");
                        String opc = sc.nextLine();
                        switch (opc) {
                            case "1":
                                workoutService.listWorkouts();
                                break;
                            case "2":
                                workoutService.listWorkouts();
                                System.out.print("Choose workout #: ");
                                int idx = Integer.parseInt(sc.nextLine()) - 1;
                                if (idx >= 0 && idx < workoutService.getWorkouts().size()) {
                                    Workout w = workoutService.getWorkouts().get(idx);
                                    int totalTime = 0;
                                    for (Exercise e : w.getExercises()) {
                                        System.out.print("Time for " + e.getName() + ": ");
                                        int t = Integer.parseInt(sc.nextLine());
                                        totalTime += t;
                                    }
                                    workoutService.logWorkout(user, w, totalTime);
                                    System.out.println("Registered! Total time: " + totalTime + "Minutes");
                                }
                                break;
                            case "3":
                                workoutService.listLogs();
                                break;
                            case "0":
                                exit = true;
                                break;
                        }
                    }
                }
                user = null;
            }
        }
    }
}