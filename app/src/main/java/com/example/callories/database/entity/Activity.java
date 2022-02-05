package com.example.callories.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "user_id"))
public class Activity {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "timestamp")
    public int timestamp;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "activity_name")
    public String activityName;

    @ColumnInfo(name = "time_of_execution")
    public int timeOfExecution;

    @ColumnInfo(name = "cal_burned")
    public int calBurned;

}
