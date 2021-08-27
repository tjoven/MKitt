package com.example.jetpack;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class InputViewModel extends ViewModel {
    private static final String TAG = "InputViewModel";
    private MutableLiveData<String> inputString = new MutableLiveData<>();

    public MutableLiveData<String> getInputString() {
        return inputString;
    }

    public void setInputString(MutableLiveData<String> inputString) {
        Log.d(TAG,"setInputString: "+inputString.getValue());
        this.inputString = inputString;
    }
}
