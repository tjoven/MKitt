package com.example.image;

import android.graphics.Bitmap;

/**
 * 图片加载事件监听器
 * @author mkitt
 */
public interface ImageRequestListener {
    /**
     * 加载图片开始时触发回调
     */
    void onLoadStart();

    /**
     * 加载图片成功后触发回调
     * @param resource 加载成功的bitmap。
     * 注意，如果ImageRequest里面设置了ImageView，那么此处的resource为null
     */
    void onLoadSuccess(Bitmap resource);

    /**
     * 加载图片失败触发回调
     */
    void onLoadFail();
}
