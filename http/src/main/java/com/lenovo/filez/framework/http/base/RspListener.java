package com.lenovo.filez.framework.http.base;

import com.alibaba.fastjson.TypeReference;

/**
 * Desc.
 *
 * @author Jason
 * @date 2018/1/16
 */
public abstract class RspListener<T> extends TypeReference {
    public abstract void onRsp(boolean success, T obj);
}
