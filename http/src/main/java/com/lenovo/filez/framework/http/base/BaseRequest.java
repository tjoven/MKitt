package com.lenovo.filez.framework.http.base;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.filez.framework.http.HttpListener;
import com.lenovo.filez.framework.http.HttpRequest;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Desc.
 *
 * @author
 * @date
 */
public abstract class BaseRequest implements HttpRequest, HttpListener<JSONObject>, Serializable {

    public static final String TAG = "Filez";
    private final String TAG_DEFAULT = "TAG_BASE_REQUEST";
    /**
     * 设置为 final， 一旦确定不允许修改
     **/
    private final String mTag;
    protected transient WeakReference<Context> mContext;
    protected transient HashMap<String, Object> mParams;
    protected transient RspListener mCallBack;
    private transient boolean showLoading = true;

    public BaseRequest(Context context) {
        this.mContext = new WeakReference<>(context);
        this.mParams = new HashMap<>();
        mTag = generateTag(context);

    }

    public BaseRequest(Context context, RspListener callBack) {
        this(context);
        this.mCallBack = callBack;
    }

    protected void onDestroy() {
        this.mCallBack = null;
        if (null != mContext) {
            mContext.clear();
        }
    }

    /**
     * 每个请求对应一个唯一的tag，方便
     *
     * @param lifeCycle 代表生命周期的东西
     * @return 简单处理为 String
     */
    private String generateTag(Object lifeCycle) {
        return lifeCycle != null ? lifeCycle.toString() : TAG_DEFAULT;
    }

    public RspListener getCallBack() {
        return mCallBack;
    }

    public void setCallBack(RspListener callBack) {
        this.mCallBack = callBack;
    }

    /**
     * 初始化请求action
     */
    public abstract String getUrlAction();

    protected abstract Map<String, Object> getUrlParam();

    /**
     * 取得context
     *
     * @return
     */
    protected Context getContext() {
        return mContext.get();
    }

    /**
     * 是否自动关闭链接（跟随页面）
     *
     * @return
     */
    protected boolean isAutoClose() {
        return true;
    }

    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean isShowLoading) {
        this.showLoading = isShowLoading;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        ApiService api = retrofit.create(ApiService.class);
        return api.getJSONResult(getUrlAction(),getUrlParam());
    }

    @Override
    public Object getTag() {
        return mTag;
    }


    public String getBaseURL() {
        return FilezConfig.getBaseUrl();
    }

    /**
     * 返回重试次数
     *
     * @return
     */
    public int getRetryCount() {
        return 2;
    }

    /**
     * 重试延迟时间 单位为毫秒
     *
     * @return
     */
    public long getRetryDelay() {
        return 0;
    }

    /**
     * 重试延迟递增时间 单位为毫秒
     * delay = retrydelay + n * increaseDelay   n为 0到RetryCount
     *
     * @return
     */
    public long getRetryIncreaseDelay() {
        return 0;
    }




    /**
     * 获取token（用于放重复提交）
     *
     * @return
     */
    protected String getToken() {
        return "";
    }

    protected Map<String, Object> getHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        String token = getToken();
        return headers;
    }

    /**
     * 获取第一次请求，服务器返回的sessionID
     *
     * @return
     */
    protected String getUid() {
        return "";
    }


    protected boolean isCheckJson() {
        return true;
    }

    /**
     * 生成post参数
     *
     * @return
     */
    protected String getPostParamString() {
        String data = "";
        try {
            JSONObject jo = new JSONObject();
            if (mParams != null) {
                for (Map.Entry<String, Object> entry : mParams.entrySet()) {
                    jo.put(entry.getKey(), entry.getValue());
                }
            }
            data = jo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "request param:" + data);
        return data;
    }

    @Override
    public void onRequestResult(JSONObject result) {
        if (FilezConfig.IS_DEBUG) {
            Log.d(TAG, "response (with urlAction : " + getUrlAction() + "): " + result.toJSONString());
        }
        try {
            if (isCheckJson()) {
                if (checkJson(result)) {
                    if (mCallBack != null) {
                        mCallBack.onRsp(true, JSON.parseObject(result.toJSONString(),mCallBack.getType()));
//                        mCallBack.onRsp(true, result.getObject("data", mCallBack.getType()));
                    }
                } else {
                    if (mCallBack != null) {
                        mCallBack.onRsp(false, null);
                    }
                }
            } else {
                /* 非mapi通用response */
                if (mCallBack != null) {
                    mCallBack.onRsp(true, result.toJavaObject(mCallBack.getType()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mCallBack.onRsp(false, null);
        }
    }

    /**
     * 开始请求触发
     */
    public void onRequestStart() {

    }

    /**
     * 请求失败触发
     *
     * @param e
     */
    public void onRequestError(Throwable e) {

    }

    /**
     * 检查返回的json数据是否合法
     */
    protected boolean checkJson(JSONObject result) {

        return true;
    }

}
