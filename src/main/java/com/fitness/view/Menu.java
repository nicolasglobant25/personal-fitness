package com.fitness.view;

import com.fitness.model.Admin;
import com.fitness.model.User;
import com.fitness.model.Exercise;
import com.fitness.model.Workout;
import com.fitness.service.ExerciseService;
import com.fitness.service.UserService;
import com.fitness.service.WorkoutService;

import java.util.Scanner;

public class Menu {

    private UserService userService;
    private ExerciseService exerciseService;
    private WorkoutService workoutService;
    private Scanner sc;

    public Menu(UserService userService, ExerciseService exerciseService, WorkoutService workoutService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.workoutService = workoutService;
        this.sc = new Scanner(System.in);
    }

    public void startMenu(){
        System.out.println("\nWelcome");
        while(true){
            User user = showLoginRegisterMenu();
            if (user instanceof Admin) {
                showAdminMenu((Admin) user);
            }else if(user instanceof User){
                showUserMenu(user);
            }
        }
    }

    public User showLoginRegisterMenu(){
        while(true){
            System.out.println("\n1. Register\n2. Login");
            String op = sc.nextLine();

            if(op.equals("1")){
                System.out.println("First Name: ");
                String firstName = sc.nextLine();
                System.out.println("Last Name: ");
                String lastName = sc.nextLine();
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Password: ");
                String password = sc.nextLine();

                userService.register(firstName,lastName,email,password);
                System.out.println("Registered, please login");
            } else if (op.equals("2")) {
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Password: ");
                String password = sc.nextLine();

               User user = userService.login(email,password);

               if(user != null){
                   System.out.println("Correct!");
                   return user;
               }else {
                   System.out.println("Invalid credentials!.");
               }


            }
        }
    }

    public void showAdminMenu(Admin admin){
        boolean exit = false;
        while(!exit){
            System.out.println("\n1. View workouts\n2. Add exercise\n3. Remove exercise\n4. Add workout\n5. Remove workout\n0. Exit");
            String opc = sc.nextLine();
            if(opc.equals("1")){
                workoutService.listWorkouts();
            }else if(opc.equals("2")){
                System.out.println("Exercise name: ");
                String exerciseName = sc.nextLine();
                System.out.println("Reps: ");
                int reps = Integer.parseInt(sc.nextLine());
                System.out.println("Sets: ");
                int sets = Integer.parseInt(sc.nextLine());

                Exercise ex = new Exercise(exerciseName,reps,sets);
                exerciseService.addExercise(ex,admin);

            } else if (opc.equals("3")) {
                exerciseService.listExercises();
                System.out.println("Enter the name of the exercise to remove: ");
                String removeExercise = sc.nextLine();

                Exercise deleteExercise = null;

                for (Exercise e: exerciseService.getExercises()) {
                    if (e.getName().equalsIgnoreCase(removeExercise)) {
                        deleteExercise = e;
                        break;
                    }
                }

                if (deleteExercise != null) {
                    exerciseService.removeExercise(deleteExercise,admin);
                } else {
                    System.out.println("Exercise not found.");
                }

            }else if(opc.equals("4")){
                System.out.println("Name :");
                String workoutName = sc.nextLine();
                System.out.println("Description :");
                String workoutDescription = sc.nextLine();
                Workout workout = new Workout(workoutName,workoutDescription);

                System.out.println("Add exercise : (coma separated):");
                exerciseService.listExercises();
                String line = sc.nextLine();

                String[] names = line.split(",");

                for(int i = 0; i < names.length; i++){
                    String exName = names[i];
                    for (Exercise e: exerciseService.getExercises()) {
                        if (e.getName().equalsIgnoreCase(exName)) {
                            workout.getExercises().add(e);
                        }
                    }
                }
                workoutService.addWorkout(workout,admin);
            } else if (opc.equals("5")) {
                workoutService.listWorkouts();
                System.out.println("Remove workout #: ");
                int removeIndex = Integer.parseInt(sc.nextLine()) -1;

                if(removeIndex >= 0 && removeIndex < workoutService.getWorkouts().size()){
                        Workout removeWorkout = workoutService.getWorkouts().get(removeIndex);
                        workoutService.removeWorkout(removeWorkout,admin);
                        System.out.println("Workout removed: " + removeWorkout.getName() + "!");
                } else {
                    System.out.println("invalid number. ");
                }
            } else if (opc.equals("0")){
                exit = true;
            }
        }

    }

    public void showUserMenu(User user){
        boolean exit = false;
        while(!exit){
            System.out.println("\n1. View workouts \n2. Register workout\n3. View logs\n0. Exit");
            String opc = sc.nextLine();

            if(opc.equals("1")){
                workoutService.listWorkouts();
            }else if(opc.equals("2")){
                workoutService.listWorkouts();
                System.out.println("Choose workout #:");
                int idx = Integer.parseInt(sc.nextLine()) - 1;

                if(idx >= 0 && idx < workoutService.getWorkouts().size()){
                    Workout workout = workoutService.getWorkouts().get(idx);
                    int totalTime = 0;

                    for (Exercise e: workout.getExercises()) {
                        System.out.println("Time for " +  e.getName() + ": ");
                        int time = Integer.parseInt(sc.nextLine());
                        totalTime += time;
                    }

                    workoutService.logWorkout(user,workout,totalTime);
                    System.out.println("Registered! Total time: " + totalTime + "Minutes");
                } else {
                    System.out.println("invalid workout. ");
                }
            } else if(opc.equals("3")){
                workoutService.listLogs();
            }else if(opc.equals("0")){
                exit = true;
            }
        }
    }
}
