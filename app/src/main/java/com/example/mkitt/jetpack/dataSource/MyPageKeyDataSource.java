package com.example.mkitt.jetpack.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

/**
 * 适用于 以页的方式请求数据
 */
public class MyPageKeyDataSource extends PageKeyedDataSource {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
}
