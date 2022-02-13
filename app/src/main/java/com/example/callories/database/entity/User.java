package com.example.callories.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "neck_cm", defaultValue = "0")
    public int neckCm;

    @ColumnInfo(name = "belly_cm", defaultValue = "0")
    public int bellyCm;

    @ColumnInfo(name = "age", defaultValue = "0")
    public int age;

    @ColumnInfo(name = "weight", defaultValue = "0.0")
    public double weight;

    @ColumnInfo(name = "imt", defaultValue = "0.0")
    public double imt;

    @ColumnInfo(name = "extra_weight_in_cal", defaultValue = "0")
    public int extraWeightInCal;

    @ColumnInfo(name = "basal_metabolism", defaultValue = "0")
    public int basalMetabolism;

    @ColumnInfo(name = "daily_burn_cal_for_a_goal", defaultValue = "0")
    public int dailyBurnCalForAGoal;

    @ColumnInfo(name = "date_goal_end", defaultValue = "1/1/2020")
    public String dateGoalEnd;

    @ColumnInfo(name = "kg_goal", defaultValue = "0.0")
    public double kgGoal;

    @ColumnInfo(name = "height", defaultValue = "0")
    public int height;

    @ColumnInfo(name = "activity_level", defaultValue = "0")
    public int activityLevel;

    @ColumnInfo(name = "is_remember_me", defaultValue = "false")
    public Boolean isRememberMe;
}