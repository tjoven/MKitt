package com.example.download;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 文件下载请求
 * @author lianbin.xu
 */

public class FileDownloadRequestImpl extends FileDownloadRequest {
    private static final String TAG = FileDownloadRequestImpl.class.getSimpleName();
    private String url;
    private Context context;
    private String fileName;
    private FileDownloadListener listener;
    private FileDownloadUiCallback callback;
    private Disposable disposable;
    private long lastReadBytes;
    private boolean complete;
    private boolean isDownloading = false;

    public FileDownloadRequestImpl(Context context, String url, String fileSavePath) {
        this.context = context;
        this.url = url;
        this.fileName = fileSavePath;
    }

    @Override
    public void cancel() {
        complete = true;
        stop();
    }

    private void stop(){
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
        isDownloading = false;
    }

    @Override
    public void pause() {
        if (complete) {
            return;
        }
        stop();
        if (callback != null) {
            lastReadBytes = callback.getReadBytes();
        }
    }

    @Override
    public void resume() {
        if (complete) {
            return;
        }
        callback.setOffset(lastReadBytes);
        download(true);
    }

    @Override
    public boolean isDownloading() {
        return isDownloading;
    }

    @Override
    public void reload() {
        cancel();
        lastReadBytes = 0;
        complete = false;
        download(false);
    }

    public void download(final boolean isResume) {
        if (TextUtils.isEmpty(fileName)) {
            notifyError(new Exception("File path is empty"));
            return;
        }

        File file = new File(fileName);

        if(!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (!success) {
                notifyError(new Exception("Failed to create directory for the file path"));
                return;
            }
        }

        if (!isResume && file.exists()) {
            file.delete();
        }

        downloadFile();
    }

    private void downloadFile() {
        getObservable(callback)
             .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable){
                Log.d(TAG,"doOnSubscribe Thread: "+Thread.currentThread().getName());//主线程
                isDownloading = true;
                if (listener != null) {
                    listener.onRequestStart();
                }
            }
        })
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(ResponseBody responseBody){
                        Log.d(TAG,"map Thread: "+Thread.currentThread().getName());//工作线程
                        return responseBody.byteStream();
                    }
        })
//                .subscribeOn(Schedulers.io())
          .observeOn(Schedulers.io())
                .doOnNext(new Consumer<InputStream>() {
                    @Override
                    public void accept(InputStream inputStream) {//工作线程
                        Log.d(TAG,"doOnNext Thread: "+Thread.currentThread().getName());
                        saveFile(inputStream);
                    }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InputStream>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(InputStream o) {//主线程
                        complete = true;
                        isDownloading = false;
                        if (listener == null) {
                            return;
                        }

                        if (callback != null && callback.isComplete()) {
                            listener.onRequestResult(fileName);
                        } else {
                            listener.onRequestError(new Exception("File not download complete"));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        notifyError(t);
                    }

                    @Override
                    public void onComplete() {
                        //do nothing
                    }
        });
    }

    private void saveFile(InputStream inputStream) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(fileName), true);
            byte[] data = new byte[1024];

            while (true) {
                int read = inputStream.read(data);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(data, 0, read);
            }
            fileOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
//            Logger.e(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }catch (IOException e){
//                    LogUtil.e(e.getMessage());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }catch (IOException e){
//                    LogUtil.e(e.getMessage());
                }
            }
        }
    }

    private void notifyError(Throwable t) {
        if (listener != null) {
            listener.onRequestError(t);
        }
        complete = true;
        isDownloading = false;
    }

    @Override
    public void start(final FileDownloadListener listener) {
        this.listener = listener;
        this.callback = new FileDownloadUiCallback(new Handler(context.getMainLooper()), listener);
        download(false);
    }

    private Observable<ResponseBody> getObservable(FileDownloadUiCallback listener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new DownloadProgressInterceptor(listener));
        builder.writeTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(5, TimeUnit.MINUTES);
        builder.connectTimeout(1, TimeUnit.MINUTES);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .baseUrl("https://download.10101111cdn.com/")
                .build();

        FileDownloadAPI service = retrofit.create(FileDownloadAPI.class);
        return service.downloadFile("bytes=" + lastReadBytes + "-", url);
    }
}
