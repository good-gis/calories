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

    @ColumnInfo(name = "neck_cm")
    public int neckCm;

    @ColumnInfo(name = "belly_cm")
    public int bellyCm;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "is_remember_me")
    public Boolean isRememberMe;
}