package com.lenovo.filez.framework.http.impl;

import android.util.Log;

import com.lenovo.filez.framework.http.HttpConfig;
import com.lenovo.filez.framework.http.HttpListener;
import com.lenovo.filez.framework.http.HttpRequest;
import com.lenovo.filez.framework.http.HttpRequestProxy;
import com.lenovo.filez.framework.http.HttpService;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lenovo.filez.framework.http.HttpServiceProvider.TAG;

/**
 * @author tjoven
 */
public class HttpServiceImpl implements HttpService {

    private HttpConfig config;
    private ConcurrentHashMap<Object, CompositeDisposable> mapping = new ConcurrentHashMap<>();
    private HashMap<String, Retrofit> mCachedRetrofit = new HashMap<>();

    public HttpServiceImpl(HttpConfig config) {
        this.config = config;
    }

    @Override
    public <T> void sendRequest(HttpRequest orgRequest, final HttpListener<T> listener) {
        final HttpRequest request = httpRequestIntercept(orgRequest);
        Retrofit retrofit = getRetrofit(request);

        Observable observable = request.getObservable(retrofit);
        if(observable == null){
            return;
        }
        //noinspection unchecked
        observable.doOnSubscribe(new Consumer() {
                    @Override
                    public void accept(Object o)  {
                        Log.d(TAG,"accept Thread: "+Thread.currentThread().getName());
                        listener.onRequestStart();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe Thread: "+Thread.currentThread().getName());
                        Object tag = request.getTag();
                        if (tag == null) {
                            return;
                        }
                        //减少一次new的可能性
                        CompositeDisposable compositeDisposable = mapping.get(tag);
                        if (compositeDisposable == null){
                            compositeDisposable = new CompositeDisposable();
                            mapping.put(tag, compositeDisposable);
                            Log.d("tzw","compositeDisposable "+tag);
                        }
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(T o) {
                        Log.d(TAG,"onNext Thread: "+Thread.currentThread().getName());
                        listener.onRequestResult(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError: "+e.toString());
                        if (listener != null) {
                            listener.onRequestError(e);
                        }
                        if (request.getTag() == null) {
                            return;
                        }
                        CompositeDisposable compositeDisposable = mapping.get(request.getTag());
                        if (compositeDisposable == null) {
                            return;
                        }
                        compositeDisposable.clear();
                    }

                    @Override
                    public void onComplete() {
                        //do nothing
                    }
                });
    }

    @Override
    public void cancelRequest(Object tag) {
        Log.d(TAG,"cancelRequest "+tag);
        Log.d("tzw","cancelRequest "+tag);
        if (tag == null) {
            return;
        }
        CompositeDisposable compositeDisposable = mapping.remove(tag);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void cancelAll() {
        Set set = mapping.keySet();
        for (Object key : set) {
            cancelRequest(key);
        }
    }

    private Retrofit getRetrofit(HttpRequest request) {
        String baseURL = request.getBaseURL();
        Retrofit retrofit = mCachedRetrofit.get(baseURL);
        if (retrofit == null) {
            Log.d(TAG,"new retrofit");
            retrofit = new Retrofit.Builder()
                    .client(okHttpConfig().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseURL)
                    .build();
            mCachedRetrofit.put(baseURL, retrofit);
        }
        return retrofit;
    }

    private OkHttpClient.Builder okHttpConfig() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeout(), TimeUnit.SECONDS);
        // .addInterceptor(new ResposeStatusInterceptor());//配置错误码拦截操作，如非2XX code直接抛出异常

        if (config.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.readTimeout(config.getReadTimeout(), TimeUnit.SECONDS);
        builder.writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS);
        return builder;
    }

    private HttpRequest httpRequestIntercept(HttpRequest httpRequest) {
        return new HttpRequestProxy(httpRequest);
    }

}
