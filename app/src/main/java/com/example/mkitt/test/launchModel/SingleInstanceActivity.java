package com.example.mkitt.test.launchModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mkitt.R;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }
}