package com.example.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片请求类
 * 用于加载图片的属性并加载图片
 * @author kitt
 */
public abstract class ImageRequest {
    /**
     * 设置在图片未加载成功之前显示的占位符图片
     * @param resId 占位符图片的资源ID
     * @return ImageRequest对象
     */
    public abstract ImageRequest placeHolder(int resId);

    /**
     * 设置加载失败的占位符图片
     * @param resId 失败占位符图片的资源ID
     * @return ImageRequest对象
     */
    public abstract ImageRequest errorPlaceHolder(int resId);


    /**
     * 设置显示的图片长宽
     * @param width 图片的宽度
     * @param height 图片的高度
     * @return ImageRequest对象
     */
    public abstract ImageRequest size(int width, int height);


    /**
     * 设置标志位，该标志位可以用于暂停请求，继续请求
     * @param tag 标志位
     * @return ImageRequest对象
     */
    public abstract ImageRequest tag(Object tag);


    /**
     * 设置是否使用内存缓存，默认是使用内存缓存的
     * @param useMemoryCache 是否使用内存缓存,
     * @return ImageRequest对象
     */
    public abstract ImageRequest useMemoryCache(boolean useMemoryCache);

    /**
     * 设置磁盘缓存策略
     *
     * @param strategy 缓存策略
     * @return ImageRequest对象
     */
    public abstract ImageRequest diskCacheStrategy(DiskCacheTactic strategy);


    /**
     * 链式调用的最后一步：执行图片加载
     * @param context 上下文
     * @param imageView 加载图片的ImageView
     */
    public abstract void into(Context context, ImageView imageView);


    /**
     * 链式调用的最后一步：执行图片加载
     * @param context 上下文
     * @param listener ImageRequestListener回调
     */
    public abstract void into(Context context, ImageRequestListener listener);


    /**
     * 设置图片为圆形
     * @param isCircle 是否为圆形
     * @return ImageRequest对象
     */
    public abstract ImageRequest circleCrop(boolean isCircle);

    /**
     * 设置图片圆角
     * @param roundingRadius 图片圆角
     * @return ImageRequest对象
     */
    public abstract ImageRequest roundedCorners(int roundingRadius);

}
