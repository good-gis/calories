package com.example.callories.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "user_id"))
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "food_name")
    public String foodName;

    @ColumnInfo(name = "qty")
    public String qty;

    @ColumnInfo(name = "cal")
    public int cal;

    @ColumnInfo(name = "protein")
    public int protein;

    @ColumnInfo(name = "fat")
    public int fat;

    @ColumnInfo(name = "carb")
    public int carb;

    @ColumnInfo(name = "timestamp")
    public int timestamp;

    @ColumnInfo(name = "user_id")
    public int userId;

}
