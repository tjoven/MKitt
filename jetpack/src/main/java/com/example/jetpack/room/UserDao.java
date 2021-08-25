package com.example.jetpack.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getAll();

    @Update
    void updateUser(UserEntity... users);

    @Insert
    void insertUser(UserEntity... users);

    @Delete
    void deleteUser(UserEntity... users);
}
