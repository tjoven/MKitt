package com.example.mkitt.test;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {

//    @GET("{url}")
//    Call<Entry> getData(@Path("url") String url, @QueryMap Map<String,String> params);
    @GET("{url}")
    Call<JSONObject> getData(@Path("url") String url, @QueryMap Map<String, String> params);

}
