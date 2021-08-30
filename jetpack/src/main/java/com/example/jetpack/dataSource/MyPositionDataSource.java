package com.example.jetpack.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

/**
 * 	从某个位置 加载一定数量，
 * 	适用于加载总量确定
 */
public class MyPositionDataSource extends PositionalDataSource<Movie> {

    //页面首次加载收据
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Movie> callback) {

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {

    }
}
