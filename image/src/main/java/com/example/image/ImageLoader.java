package com.example.image;

import android.net.Uri;

import com.example.image.glide.ImageRequestImp;

import java.io.File;

/**
 * 加载图片资源类
 */
public class ImageLoader {

    private ImageLoader() {
        throw  new IllegalMonitorStateException("ImageLoader shall not be instantiated");
    }


    /**
     * 通过特定的File加载本地图片
     * <p>
     * 同样可以调用 {@link #load(Uri) load(Uri.fromFile(file))}.
     *
     * @see #load(Uri)
     * @see #load(String)
     *
     * @param file 包含图片的文件
     * @return ImageRequest 对象
     */
    public static ImageRequest load(File file){
        return load(Uri.fromFile(file));
    }

    /**
     * 通过特定的Uri加载本地或网络图片
     *
     * @see #load(File)
     * @see #load(String)
     *
     * @param uri 包含图片地址的uri
     * @return ImageRequest 对象
     */
    public static ImageRequest load(Uri uri){
        return new ImageRequestImp(uri);
    }

    /**
     * 通过特定的path加载本地或者网络图片
     * <p>
     * 同样可以调用 {@link #load(Uri) load(Uri.fromFile(file))}.
     *
     * @see #load(Uri)
     * @see #load(File)
     *
     * @param path 包含图片地址的path
     * @return ImageRequest 对象
     */
    public static ImageRequest load(String path){
        return load(Uri.parse(path));
    }
}
