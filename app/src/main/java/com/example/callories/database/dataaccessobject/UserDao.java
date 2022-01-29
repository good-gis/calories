package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.callories.database.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE phone LIKE :phone LIMIT 1")
    User findByPhone(String phone);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}