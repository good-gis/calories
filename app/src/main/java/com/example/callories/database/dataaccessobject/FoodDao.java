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

    @Insert
    void insert(Food food);

    @Update
    public void updateFood(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food where uid = :uid")
    void deleteByID(int uid);
}