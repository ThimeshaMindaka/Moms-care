package com.example.momscare.Workout.DataBase;

public class WorkOut {
    private String workoutID;
    private String workoutName;
    private String workoutPackage;
    private String workoutDuration;
    private String workoutCalorie;
    private String workoutSteps;

    public WorkOut() {
    }

    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutPackage() {
        return workoutPackage;
    }

    public void setWorkoutPackage(String workoutPackage) {
        this.workoutPackage = workoutPackage;
    }

    public String getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutDuration(String workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public String getWorkoutCalorie() {
        return workoutCalorie;
    }

    public void setWorkoutCalorie(String workoutCalorie) {
        this.workoutCalorie = workoutCalorie;
    }

    public String getWorkoutSteps() {
        return workoutSteps;
    }

    public void setWorkoutSteps(String workoutSteps) {
        this.workoutSteps = workoutSteps;
    }
}
