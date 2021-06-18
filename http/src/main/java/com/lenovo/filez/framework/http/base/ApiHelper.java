package com.lenovo.filez.framework.http.base;



import com.lenovo.filez.framework.http.HttpConfig;
import com.lenovo.filez.framework.http.HttpService;
import com.lenovo.filez.framework.http.HttpServiceProvider;


/**
 * Desc.
 *
 * @author car
 * @date 2017/12/26
 */
public class ApiHelper {

    private static final int HTTP_TIMEOUT_SEC = 30;
    private static volatile HttpService sHttpService;

    private ApiHelper() {
        // 只支持静态方法
    }

    private static HttpService getHttpService() {
        if (sHttpService == null) {
            synchronized (ApiHelper.class) {
                if (sHttpService == null) {
                    HttpConfig config = new HttpConfig();
                    config.setTimeout(HTTP_TIMEOUT_SEC);
                    config.setReadTimeout(HTTP_TIMEOUT_SEC);
                    sHttpService = HttpServiceProvider.createHttpService(config);
                }
            }
        }
        return sHttpService;
    }

    public static void sendRequest(BaseRequest request) {
        getHttpService().sendRequest(request, request);
    }

    /**
     * 取消 tag 下的所有网络请求
     * @param tag 标签。一个 tag 通常代表一个生命周期，Activity 或 Fragment
     */
    public static void cancelRequestUnderTag(Object tag){
        getHttpService().cancelRequest(tag);
    }
}
