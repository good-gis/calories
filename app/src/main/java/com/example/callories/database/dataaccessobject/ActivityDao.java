package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.callories.database.entity.Activity;

@Dao
public interface ActivityDao {
    @Insert
    void insertAll(Activity... activities);

    @Insert
    void insert(Activity activity);

    @Update
    public void updateActivity(Activity activity);

    @Delete
    void delete(Activity activity);
}