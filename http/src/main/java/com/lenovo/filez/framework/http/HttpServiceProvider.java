package com.lenovo.filez.framework.http;
import com.lenovo.filez.framework.http.impl.HttpServiceImpl;

/**
 * HttpService请求服务提供类
 */
public class HttpServiceProvider {

    public static final String TAG = "HTTP_SERVICE";
    /**
     * 私有构造方法，禁止创建对象
     */
    private HttpServiceProvider(){}

    /**
     * 创建HttpService
     * @param config http请求的配置信息
     * @return HttpService实例
     */
    public static HttpService createHttpService(HttpConfig config){
        return new HttpServiceImpl(config);
    }
}
