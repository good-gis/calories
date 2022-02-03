package com.example.callories.database.dataaccessobject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.callories.database.entity.User;

import java.util.List;

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE phone LIKE :phone LIMIT 1")
    User findByPhone(String phone);

    @Query("SELECT * FROM user WHERE is_remember_me = 'true' LIMIT 1")
    User findRememberMeUser();

    @Insert
    void insertAll(User... users);

    @Update
    public void updateUsers(User... users);

    @Update
    public void updateUser(User user);

    @Delete
    void delete(User user);
}