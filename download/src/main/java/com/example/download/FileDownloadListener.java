package com.example.download;
/**
 * 文件下载事件监听器
 * @author lianbin.xu
 */
public interface FileDownloadListener extends HttpListener<String>{
    /**
     * 文件下载进度通知
     * @param bytesRead 当前已下载的字节数
     * @param allBytes 文件总的字节数
     */
    void onProgressUpdate(long bytesRead, long allBytes);
}
