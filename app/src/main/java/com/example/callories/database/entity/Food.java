package com.example.callories.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "user_id"))
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "food_name")
    public String foodName;

    @ColumnInfo(name = "qty")
    public String qty;

    @ColumnInfo(name = "cal")
    public Integer cal;

    @ColumnInfo(name = "protein")
    public Integer protein;

    @ColumnInfo(name = "fat")
    public Integer fat;

    @ColumnInfo(name = "carb")
    public Integer carb;

    @ColumnInfo(name = "date_created")
    public Date date;

    @ColumnInfo(name = "user_id")
    public Integer userId;

}
