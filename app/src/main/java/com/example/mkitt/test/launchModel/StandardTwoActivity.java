package com.example.mkitt.test.launchModel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mkitt.R;

public class StandardTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_two);

    }

    public void jump(View view) {
        Intent intent = new Intent(this,SingleInstanceActivity.class);
        startActivity(intent);
    }
}