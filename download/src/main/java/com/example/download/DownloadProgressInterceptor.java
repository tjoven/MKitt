package com.example.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Interceptor for download
 * Created by JokAr on 16/5/11.
 * @author lianbin.xu
 */
public class DownloadProgressInterceptor implements Interceptor {

    private FileDownloadUiCallback listener;

    public DownloadProgressInterceptor(FileDownloadUiCallback listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}
