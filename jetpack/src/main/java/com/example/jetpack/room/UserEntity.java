package com.example.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate= true)
    public Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public Integer age;

    @Ignore
    public UserEntity(Integer id) {
        this.id = id;
    }

    public UserEntity(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Ignore
    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
