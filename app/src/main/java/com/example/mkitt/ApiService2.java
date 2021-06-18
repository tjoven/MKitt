package com.example.mkitt;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  ApiService2 {
    @GET("/v3/weather/weatherInfo")
    Call getTest1(@Query("city") String city, @Query("key") String key);
}
