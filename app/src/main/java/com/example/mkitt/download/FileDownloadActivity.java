package com.example.mkitt.download;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.example.download.FileDownloadListener;
import com.example.download.FileDownloadRequest;
import com.example.mkitt.R;

import java.io.File;


/**
 * @author kitt
 */
public class FileDownloadActivity extends Activity implements View.OnClickListener {
    public static final String TAG = FileDownloadActivity.class.getSimpleName();
    private TextView resultTextView;
    private ProgressBar progressBar;
    private FileDownloadRequest request;
    private Button pauseResumeButton;
    private boolean isPaused = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_download);
        Button startButton = findViewById(R.id.start_btn);
        startButton.setOnClickListener(this);
        Button cancelButton = findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(this);

        pauseResumeButton = findViewById(R.id.pause_resume_btn);
        pauseResumeButton.setOnClickListener(this);

        progressBar = findViewById(R.id.progress_bar);
        resultTextView = findViewById(R.id.result_text);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_btn){
            if(request!=null && request.isDownloading()){
                Toast.makeText(FileDownloadActivity.this,"正在下载。。。。",Toast.LENGTH_SHORT).show();
            }else {
                startDownload();
            }
        }else if (view.getId() == R.id.cancel_btn){
            if(request != null){
                request.cancel();
                resetProgressBar();
            }
        }else if(view.getId() == R.id.pause_resume_btn){
            if(request == null){
                return;
            }
            if(isPaused){
                request.resume();
                pauseResumeButton.setText("暂停下载");
            }else{
                request.pause();
                pauseResumeButton.setText("继续下载");
            }
            isPaused = !isPaused;
        }
    }

    String url = "https://tenfei02.cfp.cn/creative/vcg/veer/1600water/veer-157690726.jpg";
    String name = "kittDemoDownload.jpg";
//    String url = "https://download.10101111cdn.com/ucarcdnstore/apk/SZZC_CD_C.apk";
//String name = "kittDemoDownload.apk";
    private void startDownload() {
        resultTextView.setText("");
        resetProgressBar();

        File directory = this.getExternalCacheDir();
        String filePath = directory.getAbsolutePath();
        String fileName = filePath + File.separator + name;
        request = FileDownloadRequest.createRequest(this, url,fileName);
        request.start(new FileDownloadListener(){
            @Override
            public void onRequestStart() {
                //provide real implementation
            }

            @Override
            public void onRequestResult(String fileName) {
                Log.i(TAG, "File is saved with full name: " + fileName);
                resultTextView.setText("File is saved with full name: " + fileName);
                resultTextView.setTextColor(getResources().getColor(android.R.color.background_dark));
                progressBar.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }

            @Override
            public void onRequestError(Throwable e) {
                resultTextView.setText(e.getMessage());
                resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                progressBar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            }

            @Override
            public void onProgressUpdate(long readBytes, long totalBytes ) {
                int progress = (int)(((double)readBytes / totalBytes) *100);
                progressBar.setProgress(progress);
            }
        });
    }

    private void resetProgressBar(){
        progressBar.setProgress(0);
        progressBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}
