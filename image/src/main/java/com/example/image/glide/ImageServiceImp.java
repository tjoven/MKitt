package com.example.image.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.image.ImageRequest;
import com.example.image.ImageRequestListener;


/**
 * @author mkitt
 */
public class ImageServiceImp {
    public void sendRequest(Context context, ImageRequest request) {
        ImageRequestImp imageRequestImp = (ImageRequestImp) request;
        RequestBuilder drawableTypeRequest = Glide.with(context).load(imageRequestImp.getUri());
        final ImageRequestListener listener = imageRequestImp.getListener();
        RequestOptions options = new RequestOptions();
        // 设置背景图
        if (imageRequestImp.getErrorPlaceHolderId() > 0) {
            options = options.error(imageRequestImp.getErrorPlaceHolderId());
        }
        if (imageRequestImp.getPlaceHolderId() > 0) {
            options = options.placeholder(imageRequestImp.getPlaceHolderId());
        }

        options.skipMemoryCache(!imageRequestImp.isUseMemoryCache());

        // 设置缓存
        if (imageRequestImp.getDiskCacheStrategy() != null) {
            switch (imageRequestImp.getDiskCacheStrategy()) {
                case ALL:
                    options = options.diskCacheStrategy(DiskCacheStrategy.ALL);
                    break;
                case NONE:
                    options = options.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
                case SOURCE:
                    options = options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                    break;
                case RESULT:
                    options = options.diskCacheStrategy(DiskCacheStrategy.DATA);
                    break;
                default:break;
            }
        }

        // 是否裁剪图片
        if (imageRequestImp.getHeight() > 0 || imageRequestImp.getWidth() > 0) {
            options = options.override(imageRequestImp.getWidth(), imageRequestImp.getHeight());
        }
        // 设置为圆形图片
        if (imageRequestImp.isCircle()) {
            options = options.transform(new CircleCrop());
        }
        // 设置图片圆角
        if (imageRequestImp.getRoundingRadius() > 0) {
            options = options.transform(new RoundedCorners(imageRequestImp.getRoundingRadius()));
        }

        if (imageRequestImp.getImageView() != null) {
            drawableTypeRequest.apply(options).into(imageRequestImp.getImageView());

        }

        if (listener != null) {
            listener.onLoadStart();
            drawableTypeRequest.apply(options).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition transition) {
                    listener.onLoadSuccess(resource);
                }

            });
        }
    }

    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }
}
