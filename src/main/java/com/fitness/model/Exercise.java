package com.fitness.model;

public class Exercise {
    private String name;
    private int repetitions;
    private int sets;

    public Exercise(String name, int repetitions,  int sets) {
        this.name = name;
        this.repetitions = repetitions;
        this.sets = sets;
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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                '}';
    }
}
