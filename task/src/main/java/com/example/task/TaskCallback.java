package com.example.task;

/**
 * @author lianbin.xu
 */

public interface TaskCallback<T> {
    /**
     * 任务结束后会通过此方法回调
     * 默认会在主线程回调此方法
     * @param result 回调结果
     */
    void onResult(T result);
}
