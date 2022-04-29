package com.example.download;

import android.content.Context;


/**
 * 文件下载请求类
 * @author lianbin.xu
 */
public abstract class FileDownloadRequest {
    /**
     * 创建一个文件下载请求
     * @param context      上下文信息
     * @param url          文件的下载url，必须是http或者https的url
     * @param fileSavePath 文件下载完成后的绝对存储路径，包括文件名
     * @return 文件下载请求
     */
    public static FileDownloadRequest createRequest(Context context, String url, String fileSavePath){
        return new FileDownloadRequestImpl(context, url, fileSavePath);
    }

    /**
     * 启动文件下载
     * @param listener 文件下载监听器
     */
    public abstract void start(FileDownloadListener listener);

    /**
     * 取消文件的下载
     */
    public abstract void cancel();

    /**
     * 暂停文件下载，可以通过resume来恢复
     */
    public abstract void pause();

    /**
     * 继续文件下载
     */
    public abstract void resume();

    /**
     * 查询文件是否在下载
     * @return 查询文件是否在下载， true表示正在下载，false表示未开始或者已下载
     */
    public abstract boolean isDownloading();

    /**
     * 重新下载
     */
    public abstract void reload();
}
