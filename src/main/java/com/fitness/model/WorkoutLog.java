package com.fitness.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WorkoutLog {
    private LocalDate date;
    private Workout workout;
    private Map<String, Integer> exerciseTime = new HashMap<>();

    public WorkoutLog(LocalDate date, Workout workout) {
        this.date = date;
        this.workout = workout;
    }

    public Map<String, Integer> getExerciseTime() {
        return exerciseTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public Workout getWorkout() {
        return workout;
    }

    @Override
    public String toString() {
        return "WorkoutLog{" +
                "date=" + date +
                ", workout=" + workout.getName() +
                ", exerciseTime=" + exerciseTime +
                '}';
    }
}
