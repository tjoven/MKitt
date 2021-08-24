package com.example.jetpack;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.logging.Logger;

public class MyViewModel extends ViewModel {
    private static final String TAG = MainActivity.TAG;
    //搭配LiveData使用
    private MutableLiveData<User> user;
    private MutableLiveData<Integer> score;

    public MutableLiveData<Integer> getScore() {
        if (score == null){
            score = new MutableLiveData<>();
            //初始值为0
            score.setValue(0);
        }
        return score;
    }

    public void addScore() {
        Log.d(TAG,"addScore");
        score.setValue(score.getValue()+1);
    }

    public void addAge() {
        Log.d(TAG,"addScore");
        user.setValue(new User(user.getValue().getName(),user.getValue().getAge()+1));
    }

    public MutableLiveData<User> getUser() {
        if (user == null){
            user = new MutableLiveData<>();
            //初始值为0
            user.setValue(new User("tianzw",10));
        }
        return user;
    }

}
