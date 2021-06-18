package com.lenovo.filez.framework.http;

import io.reactivex.Observable;

/**
 * HTTP请求服务类，用来发送/取消HttpRequest
 * HttpService一般是由HttpServiceProvider创建生成
 */
public interface HttpService {
    /**
     * 发送网络请求
     * @param request 待发送的HttpRequest
     * @param listener HttpRequest事件的监听器
     * @param <T> 服务器在http body里面返回的数据类型
     */
    <T> void sendRequest(HttpRequest request, HttpListener<T> listener);

    /**
     * 取消特定的HTTP请求
     * @param tag HttpRequest的tag
     */
    void cancelRequest(Object tag);

    /**
     * 取消全部请求
     */
    void cancelAll();

}
