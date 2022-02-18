package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.callories.database.entity.Activity;

import java.util.List;

@Dao
public interface ActivityDao {

    @Query("SELECT * FROM activity WHERE user_id = :userId")
    List<Activity> getAllForAUser(int userId);

    @Query("SELECT * FROM activity WHERE user_id = :userId and date = :date")
    List<Activity> getSportForADay(String date, int userId);

    @Insert
    void insert(Activity activity);

    @Query("DELETE FROM activity where uid = :uid")
    void deleteByID(int uid);
}