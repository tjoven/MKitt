package com.lenovo.filez.framework.http;

import io.reactivex.Observable;
import retrofit2.Retrofit;


public class HttpRequestProxy implements HttpRequest {

    private HttpRequest orgRequest;

    public HttpRequestProxy(HttpRequest orgRequest){
        this.orgRequest = orgRequest;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        if(orgRequest != null){
            return orgRequest.getObservable(retrofit);
        }
        return null;
    }

    @Override
    public String getBaseURL() {
        if(orgRequest != null){
            return orgRequest.getBaseURL();
        }
        return null;
    }

    @Override
    public HttpService.HTTPTYPE getHttpType() {
        if(orgRequest != null){
            return orgRequest.getHttpType();
        }
        return null;
    }

    @Override
    public int getRetryCount() {
        if(orgRequest != null){
            return orgRequest.getRetryCount();
        }
        return 0;
    }

    @Override
    public long getRetryDelay() {
        if(orgRequest != null){
            return orgRequest.getRetryDelay();
        }
        return 0;
    }

    @Override
    public long getRetryIncreaseDelay() {
        if(orgRequest != null){
            return orgRequest.getRetryIncreaseDelay();
        }
        return 0;
    }

    @Override
    public Object getTag() {
        if(orgRequest != null){
            return orgRequest.getTag();
        }
        return null;
    }

}
