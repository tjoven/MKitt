package com.lenovo.filez.framework.http;

/**
 * HTTP请求事件监听器
 */

public interface HttpListener<T> {
    /**
     * 开始请求触发<br>
     * 在发送请求前会在主线程回调该方法，业务层可以在此显示进度框等
     */
    void onRequestStart();

    /**
     * 请求成功触发<br>
     * 接收到从服务器返回的结果后会在主线程回调此方法，业务可以在此方法内关闭进度框，展示结果
     * @param result 响应对应的数据类
     */
    void onRequestResult(T result);

    /**
     * 请求失败触发<br>
     * 如果请求过程出错，那么该方法就会被回调，业务可以在此方法内显示错误提示等
     * @param e 异常信息
     */
    void onRequestError(Throwable e);
}
