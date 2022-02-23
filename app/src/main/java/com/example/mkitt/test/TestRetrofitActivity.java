package com.example.mkitt.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.example.mkitt.R;
import com.example.mkitt.http.Entry;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author tjoven
 * https://www.jianshu.com/p/7b839b7c5884
 *
 */
@SuppressLint("CheckResult")
public class TestRetrofitActivity extends Activity {

    // https://www.kuaidi100.com/query?type=yuantong&postid=11111111111
    private String TAG = "TestRetrofitActivity";
    String baseUrl = "https://www.kuaidi100.com";
//    String baseUrl = "https://console.box.lenovo.com/v2/sso/get_ent_login_addr/";

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        mButton =  (Button)findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request(view);
            }
        });

    }

    private void request(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String,String> params = new HashMap<>();
        params.put("type","yuantong");
        params.put("postid","11111111111");

        Call<Entry> call = apiService.getData("query",params);
        call.enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                Log.d(TAG,"onResponse: "+response.body().tzw);
                Log.d(TAG,"ThreadName: "+Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                Log.d(TAG,"onFailure "+ t.toString());
            }

        });
    }
}
