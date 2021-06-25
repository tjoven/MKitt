package com.example.mkitt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mkitt.http.Entry;
import com.example.mkitt.http.TestRequest;
import com.example.mkitt.test.TestRetrofitActivity;
import com.lenovo.filez.framework.http.base.ApiHelper;
import com.lenovo.filez.framework.http.base.RspListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });


    }

    private void request() {
        Intent intent = new Intent(this, TestRetrofitActivity.class);
        startActivity(intent);

        TestRequest request = new TestRequest(MainActivity.this,new RspListener<Entry>(){

            @Override
            public void onRsp(boolean success, Entry obj) {
                Log.d(TAG,"onRsp: "+obj.getData().toString());

            }
        });
        ApiHelper.sendRequest(request);
    }

}
