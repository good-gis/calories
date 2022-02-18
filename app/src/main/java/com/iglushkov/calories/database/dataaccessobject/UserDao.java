package com.iglushkov.calories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iglushkov.calories.database.entity.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE phone LIKE :phone LIMIT 1")
    User findByPhone(String phone);

    @Query("SELECT * FROM user WHERE is_remember_me = 'true' LIMIT 1")
    User findRememberMeUser();

    @Insert
    void insertAll(User... users);

    @Update
    void updateUser(User user);
}