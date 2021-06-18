package com.example.mkitt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.lenovo.filez.framework.http.base.ApiHelper;
import com.lenovo.filez.framework.http.base.ApiService;
import com.lenovo.filez.framework.http.base.RspListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    /**
     * https://restapi.amap.com//v3/weather/weatherInfo?city=110101&key=ae6c53e2186f33bbf240a12d80672d1b
     */
    private void test(){
//利用构建者模式实例化Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://restapi.amap.com/").build();
//通过动态代理生成接口的实例对象，并实现接口中定义的方法；
        ApiService2 apiService = (ApiService2) retrofit.create(ApiService2.class);
//代理对象去做http请求
        Call<String> call = (Call) apiService.getTest1("110101","ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
