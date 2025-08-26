package com.fitness.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

    public class WorkoutLog {
    private LocalDate date;
    private Workout workout;
    private Map<Exercise, Integer> exerciseTime = new HashMap<>();

    public WorkoutLog(LocalDate date, Workout workout) {
        this.date = date;
        this.workout = workout;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
    public Map<Exercise, Integer> getExerciseTime() {
        return exerciseTime;
    }
    public void setExerciseTime(Map<Exercise, Integer> exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    @Override
    public String toString() {
        return "WorkoutLog: " + workout.getName() + " on " + date + " | Total exercises: " + exerciseTime.size();
    }
}
