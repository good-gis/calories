package com.example.callories.model;

public class Activity {
    private int uid;
    private String date;
    private int userId;
    private String activityName;
    private int timeOfExecution;
    private int calBurned;

    public Activity(int uid, String date, int userId, String activityName, int timeOfExecution, int calBurned) {
        this.uid = uid;
        this.date = date;
        this.userId = userId;
        this.activityName = activityName;
        this.timeOfExecution = timeOfExecution;
        this.calBurned = calBurned;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(int timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public int getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(int calBurned) {
        this.calBurned = calBurned;
    }
}
