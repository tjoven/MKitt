package com.example.mkitt.test.launchModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mkitt.R;


public class BaseActivity extends AppCompatActivity {

    private final static String TAG = "ActivityStack";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.d(TAG,getClass().getSimpleName()+"  onCreate"+"  TaskId: "+getTaskId()+"  Pid: "+android.os.Process.myPid());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,getClass().getSimpleName()+"  onNewIntent"+"  TaskId: "+getTaskId()+"  Pid: "+android.os.Process.myPid());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}