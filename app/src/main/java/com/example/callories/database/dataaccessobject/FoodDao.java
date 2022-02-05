package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.callories.database.entity.Food;

@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);

    @Update
    public void updateFood(Food food);

    @Delete
    void delete(Food food);
}