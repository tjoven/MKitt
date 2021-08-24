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

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Update
    List<User> updateUser(User... users);

    @Insert
    void insertUser(User... users);

    @Delete
    void deleteUser(User... users);
}
