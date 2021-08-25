package com.example.jetpack.room;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<List<UserEntity>> users = new MutableLiveData<>();
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<List<UserEntity>> getUsers() {
        return repository.getAll();
    }

    public void insertUser(UserEntity user){
        repository.insertUser(user);
    }

    public void deleteUser(UserEntity user){
        repository.deleteUser(user);
    }

    public void updateUser(UserEntity user){
        repository.updateUser(user);
    }

}
