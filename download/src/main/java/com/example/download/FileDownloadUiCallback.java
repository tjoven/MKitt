package com.example.download;

import android.os.Handler;


/**
 * Created by lianbin.xu@ucarinc.com on 2018/1/2.
 * @author lianbin.xu
 */

public class FileDownloadUiCallback {
    private Handler handler;
    private FileDownloadListener listener;
    private boolean isComplete = false;
    private long readBytes;
    private long offset;

    public FileDownloadUiCallback(Handler handler, FileDownloadListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void update(final long bytesRead, final long contentLength, final boolean done){
        final long totalReadBytes = bytesRead + offset;
        final long totalLength = contentLength + offset;

        if(listener != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    isComplete = done;
                    readBytes = totalReadBytes;
                    listener.onProgressUpdate(totalReadBytes, totalLength);
                }
            });
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    public long getReadBytes() {
        return readBytes;
    }
}
