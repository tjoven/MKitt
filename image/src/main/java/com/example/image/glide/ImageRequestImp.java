package com.example.image.glide;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.image.DiskCacheTactic;
import com.example.image.ImageRequest;
import com.example.image.ImageRequestListener;

/**
 * @author mkitt
 */
public class ImageRequestImp extends ImageRequest {

    private int height = -1;
    private int width = -1;
    private int placeHolderId = 0;
    private int errorPlaceHolderId = 0;
    private Object tag;
    private DiskCacheTactic strategy;

    private Uri uri;

    private ImageView imageView;
    private ImageRequestListener listener;

    private boolean isCircle = false;
    private int roundingRadius = 0;
    private boolean useMemoryCache = true;


    public ImageRequestImp(Uri uri) {
        this.uri = uri;
    }
    @Override
    public ImageRequest placeHolder(int resId) {

        this.placeHolderId = resId;
        return this;

    }

    @Override
    public ImageRequest errorPlaceHolder(int resId) {
        this.errorPlaceHolderId = resId;
        return this;
    }

    @Override
    public ImageRequest size(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public ImageRequest tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public ImageRequest useMemoryCache(boolean useMemoryCache) {
        this.useMemoryCache = useMemoryCache;
        return this;
    }


    @Override
    public ImageRequest diskCacheStrategy(DiskCacheTactic strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public void into(Context context, ImageView imageView) {
        this.imageView = imageView;
        new ImageServiceImp().sendRequest(context, this);
    }

    @Override
    public void into(Context context, ImageRequestListener listener) {
        this.listener = listener;
        new ImageServiceImp().sendRequest(context, this);

    }

    @Override
    public ImageRequest circleCrop(boolean isCircle) {
        this.isCircle = isCircle;
        return this;
    }

    @Override
    public ImageRequest roundedCorners(int roundingRadius) {
        this.roundingRadius = roundingRadius;
        return this;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPlaceHolderId() {
        return placeHolderId;
    }

    public int getErrorPlaceHolderId() {
        return errorPlaceHolderId;
    }

    public Object getTag() {
        return tag;
    }

    public DiskCacheTactic getDiskCacheStrategy(){
        return strategy;
    }

    public Uri getUri() {
        return uri;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageRequestListener getListener() {
        return listener;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getRoundingRadius() {
        return roundingRadius;
    }

    public boolean isUseMemoryCache() {
        return useMemoryCache;
    }
}
