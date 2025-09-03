package com.fitness.service;

import com.fitness.model.Workout;
import com.fitness.model.WorkoutLog;
import com.fitness.model.Exercise;
import com.fitness.model.User;
import com.fitness.model.Admin;
import java.time.LocalDate;
import java.util.*;

public class WorkoutService {

    private List<Workout> workouts = new ArrayList<>();
    private List<WorkoutLog> workoutLogs = new ArrayList<>();

    public WorkoutService(List<Exercise> defaultExercises) {

        Workout cardio = new Workout("Cardio Blast", "Increase stamina and endurance");
        cardio.getExercises().add(defaultExercises.get(0));
        cardio.getExercises().add(defaultExercises.get(3));

        Workout strength = new Workout("Strength Builder", "Build muscle strength");
        strength.getExercises().add(defaultExercises.get(4));
        strength.getExercises().add(defaultExercises.get(5));

        Workout endurance = new Workout("Endurance Challenge", "Improve cardiovascular health");
        endurance.getExercises().add(defaultExercises.get(1));
        endurance.getExercises().add(defaultExercises.get(2));

        workouts.add(cardio);
        workouts.add(strength);
        workouts.add(endurance);
    }

    public void listWorkouts() {
        System.out.println("Available Workouts:");
        for (int i = 0; i < workouts.size(); i++) {
            System.out.println((i + 1) + ". " + workouts.get(i).getName() + " - " + workouts.get(i).getDescription());
        }
    }


    public void logWorkout(User user, Workout workout, List<Integer> times) {
        WorkoutLog log = new WorkoutLog(LocalDate.now(), workout);
        for (int i = 0; i < workout.getExercises().size(); i++) {
            log.getExerciseTime().put(workout.getExercises().get(i).getName(), times.get(i));
        }
        workoutLogs.add(log);
    }


    public void logWorkout(User user, Workout workout, int totalTime) {
        WorkoutLog log = new WorkoutLog(LocalDate.now(), workout);
        log.getExerciseTime().put("Total", totalTime);
        workoutLogs.add(log);
    }
    public void listLogs() {
        if (workoutLogs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        List<WorkoutLog> sortedLogs = new ArrayList<>(workoutLogs);
        sortedLogs.sort(Comparator.comparing(WorkoutLog::getDate).reversed());

        System.out.println("Workout History:");
        for (int i = 0; i < sortedLogs.size(); i++) {
            WorkoutLog log = sortedLogs.get(i);
            System.out.println((i + 1) + ". Date: " + log.getDate() + " | Workout: " + log.getWorkout().getName());
        }

        System.out.println("Enter the workout number to see details, or 0 to go back:");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice > 0 && choice <= sortedLogs.size()) {
            WorkoutLog selectedLog = sortedLogs.get(choice - 1);
            System.out.println("Workout Details:");
            System.out.println("Workout: " + selectedLog.getWorkout().getName());
            System.out.println("Date: " + selectedLog.getDate());
            selectedLog.getExerciseTime().forEach((exercise, time) ->
                    System.out.println("- " + exercise + ": " + time + " minutes")
            );
        }
    }




    public void addWorkout(Workout workout, User user) {
        if (user instanceof Admin) {
            workouts.add(workout);
            System.out.println("Workout added: " + workout.getName());
        } else {
            System.out.println("Only admins can add workouts.");
        }
    }

    public void removeWorkout(Workout workout, User user) {
        if (user instanceof Admin) {
            if (workouts.contains(workout)) {
                workouts.remove(workout);
                System.out.println("Workout removed: " + workout.getName());
            } else {
                System.out.println("Workout not found.");
            }
        } else {
            System.out.println("Only admins can remove workouts.");
        }
    }


    public List<Workout> getWorkouts() {
        return workouts;
    }


}