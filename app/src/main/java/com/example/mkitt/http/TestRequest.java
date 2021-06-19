package com.example.mkitt.http;

import android.content.Context;

import com.lenovo.filez.framework.http.base.BaseRequest;
import com.lenovo.filez.framework.http.base.RspListener;

import java.util.HashMap;
import java.util.Map;

public class TestRequest extends BaseRequest {
    public TestRequest(Context context) {
        super(context);
    }

    public TestRequest(Context context, RspListener callBack) {
        super(context, callBack);
    }

    @Override
    public String getUrlAction() {
        return "query";
    }

    @Override
    protected Map<String, Object> getUrlParam() {
        Map<String,Object> params = new HashMap<>();
        params.put("type","yuantong");
        params.put("postid","11111111111");
        return params;
    }
}
