package com.example.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by lianbin.xu@ucarinc.com on 2017/12/28.
 * @author lianbin.xu
 */

public interface FileDownloadAPI {
    /**
     * 下载文件
     * @param start 待读取的byte的位移
     * @param url 文件的地址
     * @return RxJava的Observable形式的请求对象
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Header("RANGE") String start, @Url String url);
}
