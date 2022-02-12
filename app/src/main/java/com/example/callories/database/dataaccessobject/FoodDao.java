package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.callories.database.entity.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    List<Food> getAll();

    @Query("SELECT * FROM food WHERE user_id = :userId")
    List<Food> getAllForAUser(int userId);

    @Insert
    void insert(Food food);

    @Update
    void updateFood(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food where uid = :uid")
    void deleteByID(int uid);

    @Query("SELECT * FROM food WHERE date = :date and user_id = :userId")
    List<Food> getFoodForADay(String date, int userId);
}