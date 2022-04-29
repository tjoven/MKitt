package com.example.mkitt.image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
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
    String url = "https://tenfei02.cfp.cn/creative/vcg/veer/1600water/veer-157690726.jpg";
//    String url = "http://dingyue.ws.126.net/2022/0409/76f3dd5cg00ra1xot006vc00079009ec.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = findViewById(R.id.imageView);
        ImageLoader.load(url)
//                .circleCrop(true)
//                .roundedCorners(50)
//                .size(500,200)

    .into(this,imageView);

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestOptions options = new RequestOptions();
                options
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                        .diskCacheStrategy(DiskCacheStrategy.DATA)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .skipMemoryCache(true)
                        .override(1000,400);
                Glide.with(ImageActivity.this)
                        .load(url)
                        .apply(options)
//                        .into(new SimpleTarget<Drawable>() {
//                            @Override
//                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//
//                            }
//                        })
                        .into(imageView);
            }
        });
    }
}