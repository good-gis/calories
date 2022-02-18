package com.iglushkov.calories.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.iglushkov.calories.database.dataaccessobject.ActivityDao;
import com.iglushkov.calories.database.dataaccessobject.FoodDao;
import com.iglushkov.calories.database.dataaccessobject.UserDao;
import com.iglushkov.calories.database.entity.Activity;
import com.iglushkov.calories.database.entity.Food;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.database.entity.Weight;

@Database(entities = {User.class, Activity.class, Food.class, Weight.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
    public abstract ActivityDao activityDao();
}