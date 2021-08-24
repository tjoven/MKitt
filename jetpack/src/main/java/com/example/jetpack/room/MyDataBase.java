package com.example.jetpack.room;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "database-name";
    private MyDataBase instance;

    public MyDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDataBase.class, DATABASE_NAME).build();
        }
        return instance;
    }
    public abstract UserDao getUserDao();


}
