package com.example.mkitt.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.fastjson.JSONObject;
import com.example.mkitt.R;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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
public class TestRetrofitActivity extends Activity {

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
//                request(view);
//                testRxjavaMerge();
                testRxjavaZip();
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
        params.put("ent_code","mrfile");

        Call<JSONObject> call = apiService.getData("query",params);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.d(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.d(TAG,"onFailure "+ t.toString());

            }
        });
    }

    private void testRxjavaZip(){
        String[] strs = {"a","b","c","d","e"};
        Integer[] ints = {1,2,3,4,5,6};
//        Observable observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Integer> observable1 = Observable.fromArray(ints);
        Observable<String> observable2 = Observable.fromArray(strs);
        //noinspection unchecked
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {

            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe: ");
                    }

                    @Override
                    public void onNext(String o) {
                        Log.d(TAG,"onNext: "+o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError: "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete: ");
                    }
                });
    }

    private void testRxjavaMerge(){
        String[] strs = {"a","b","c","d","e"};
        Integer[] ints = {1,2,3,4,5};
//        Observable observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Integer> observable1 = Observable.fromArray(ints);
        Observable<String> observable2 = Observable.fromArray(strs);
        //noinspection unchecked
        Observable.merge(observable1,observable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof String){
                            Log.d(TAG,"onNext String: "+o);
                        }else if(o instanceof Integer){
                            Log.d(TAG,"onNext Integer: "+o);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError: "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete: ");
                    }
                });
    }
}
