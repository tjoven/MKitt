package com.example.jetpack.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

/**
 * 适用于 目标数据的下一页 需要以来上一页数据中的最后一个对象中的某个字段作为key
 */
public class MyItemKeyedDataSource extends ItemKeyedDataSource {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @NonNull
    @Override
    public Object getKey(@NonNull Object item) {
        return null;
    }
}
