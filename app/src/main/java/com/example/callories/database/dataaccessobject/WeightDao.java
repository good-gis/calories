package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.callories.database.entity.Weight;

@Dao
public interface WeightDao {

    @Insert
    void insert(Weight weight);

    @Update
    public void updateWeight(Weight weight);

    @Delete
    void delete(Weight weight);
}
