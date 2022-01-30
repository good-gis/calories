package com.example.callories.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.callories.database.dataaccessobject.UserDao;
import com.example.callories.database.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}