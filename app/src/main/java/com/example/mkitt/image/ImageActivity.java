package com.example.mkitt.image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.image.ImageLoader;
import com.example.mkitt.R;

public class ImageActivity extends AppCompatActivity {
    private static final String TAG = "ImageActivity";
    String url = "https://tenfei02.cfp.cn/creative/vcg/veer/1600water/veer-157690726.jpg";
//    String url = "http://dingyue.ws.126.net/2022/0409/76f3dd5cg00ra1xot006vc00079009ec.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = findViewById(R.id.imageView);
//        ImageLoader.load(url)
//                .circleCrop(true)
//                .roundedCorners(50)
//                .size(500,200)

//    .into(this,imageView);

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestOptions options = new RequestOptions();
                Glide.with(ImageActivity.this).asBitmap()
                        .load(url)
                        .apply(options)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                Log.d(TAG,"宽度："+resource.getWidth());
                                Log.d(TAG,"高度："+resource.getHeight());
                            }
                        });
//                        .into(imageView);
            }
        });

    }
}