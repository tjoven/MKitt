package com.example.jetpack;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {
    private String name ;

    public User(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setN(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
