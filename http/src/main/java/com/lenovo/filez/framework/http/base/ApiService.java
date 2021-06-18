package com.lenovo.filez.framework.http.base;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author
 * @des 网络请求服务接口
 */
public interface ApiService<T> {

    @GET("{url}")
    Observable<JSONObject> getJSONResult(@Path(value = "url", encoded = true) String url, @QueryMap(encoded = true) Map<String, String> params, @HeaderMap Map<String, String> headers);

    @POST("{url}")
    @FormUrlEncoded
    Observable<JSONObject> getResult(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, Object> params);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("{url}")
    Observable<JSONObject> getResult(@Path(value = "url", encoded = true) String url, @Body RequestBody info);
}
