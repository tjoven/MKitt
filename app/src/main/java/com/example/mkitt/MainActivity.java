package com.example.mkitt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.lenovo.filez.framework.http.base.ApiHelper;
import com.lenovo.filez.framework.http.base.RspListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestRequest request = new TestRequest(MainActivity.this,new RspListener<String>(){

                    @Override
                    public void onRsp(boolean success, String obj) {

                    }
                });
                ApiHelper.sendRequest(request);
            }
        });

    }
}
