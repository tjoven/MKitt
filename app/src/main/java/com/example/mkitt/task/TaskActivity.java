package com.example.mkitt.task;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mkitt.R;
import com.example.task.AbstractTaskService;
import com.example.task.TaskCallback;
import com.example.task.internal.TaskServiceImpl;

import java.util.concurrent.Callable;

public class TaskActivity extends Activity {

    private static final String TAG = "TaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        AbstractTaskService taskService = AbstractTaskService.get();
        findViewById(R.id.bt_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskService.executeTask(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"executeTask "+Thread.currentThread().getName());
                    }
                });

                taskService.postTask(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"postTask "+Thread.currentThread().getName());
                    }
                });

                taskService.executeTask(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Log.d(TAG,"call "+Thread.currentThread().getName());
                        return " ";
                    }
                }, new TaskCallback<Object>() {
                    @Override
                    public void onResult(Object result) {
                        Log.d(TAG,"onResult "+result+"  "+Thread.currentThread().getName());
                    }
                });
            }
        });
    }
}