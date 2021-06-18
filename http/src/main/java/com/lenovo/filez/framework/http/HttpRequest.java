package com.lenovo.filez.framework.http;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * 网络请求类
 * 业务层针对每个请求，需要定义HttpRequest的实现类
 */
public interface HttpRequest {
    /**
     *获取Retrofit的Observable<br>
     * App需要返回一个RxJava的Observable对象，该对象对应一个具体的http请求，包括url，http 方法以及请求参数
     一般情况下，Observable对象由retrofit框架生成
     * @param retrofit retrofit实例
     * @return RxJava的Observable对象
     */
    Observable getObservable(Retrofit retrofit);

    /**
     * 获取url前缀
     * @return url前缀
     */
    String getBaseURL();

    /**
     * 返回失败重试次数
     * @return 失败重试次数
     */
    int getRetryCount();

    /**
     * 重试延迟时间 单位为毫秒
     * @return 重试延迟时间
     */
    long getRetryDelay();

    /**
     * 重试延迟递增时间 单位为毫秒<br>
     * n为 0到RetryCount返回网络失败后发起重试的时间延长时间<br>
     *这里，假设retry delay = D， retry increase delay = ID<br>
     *假设网络上次请求失败的时间为T，那么第n次重试的时间为<br>
     *T + D + (n-1)*ID
     * @return 重试延迟递增时间
     */
    long getRetryIncreaseDelay();

    /**
     * 获取请求标签
     * 返回该请求的tag，该tag可以用来取消请求。多个请求可以使用同一个tag
     * @return 请求的标签
     */
    Object getTag();
}
