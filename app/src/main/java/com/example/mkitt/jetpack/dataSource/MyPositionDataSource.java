package com.example.mkitt.jetpack.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

/**
 * 	从某个位置 加载一定数量，
 * 	适用于加载总量确定
 */
public class MyPositionDataSource extends PositionalDataSource {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback callback) {

    }
}
