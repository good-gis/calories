package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.callories.database.entity.Activity;

import java.util.List;

@Dao
public interface ActivityDao {
    @Insert
    void insertAll(Activity... activities);

    @Query("SELECT * FROM activity WHERE user_id = :userId")
    List<Activity> getAllForAUser(int userId);

    @Query("SELECT * FROM activity WHERE user_id = :userId and date = :date")
    List<Activity> getAllForADayForUser(String date, int userId);

    @Insert
    void insert(Activity activity);

    @Update
    public void updateActivity(Activity activity);

    @Delete
    void delete(Activity activity);

    @Query("DELETE FROM activity where uid = :uid")
    void deleteByID(int uid);
}