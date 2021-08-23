package com.example.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    //搭配LiveData使用
    private MutableLiveData<Integer> data;
    private String extend;

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getExtend() {
        return extend;
    }

    public MutableLiveData<Integer> getData() {
        if (data == null){
            data = new MutableLiveData<>();
            //初始值为0
            data.setValue(0);
        }
        return data;
    }

}
