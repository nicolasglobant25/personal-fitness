package com.fitness.model;

public class Exercise {
    private String name;
    private int repetitions;

    public Exercise(String name, int repetitions) {
        this.name = name;
        this.repetitions = repetitions;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRepetitions() {
        return repetitions;
    }
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return name + " (Reps: " + repetitions + ")";
    }
}
