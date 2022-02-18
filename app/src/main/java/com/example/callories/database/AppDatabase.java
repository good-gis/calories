package com.example.callories.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.callories.database.dataaccessobject.ActivityDao;
import com.example.callories.database.dataaccessobject.FoodDao;
import com.example.callories.database.dataaccessobject.UserDao;
import com.example.callories.database.entity.Activity;
import com.example.callories.database.entity.Food;
import com.example.callories.database.entity.User;
import com.example.callories.database.entity.Weight;

@Database(entities = {User.class, Activity.class, Food.class, Weight.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
    public abstract ActivityDao activityDao();
}