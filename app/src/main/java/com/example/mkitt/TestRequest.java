package com.example.mkitt;

import android.content.Context;

import com.lenovo.filez.framework.http.base.BaseRequest;
import com.lenovo.filez.framework.http.base.RspListener;

public class TestRequest extends BaseRequest {
    public TestRequest(Context context) {
        super(context);
    }

    public TestRequest(Context context, RspListener callBack) {
        super(context, callBack);
    }

    @Override
    public String getUrlAction() {
        return "";
    }
}
