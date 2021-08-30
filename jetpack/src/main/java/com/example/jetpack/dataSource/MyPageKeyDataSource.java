package com.example.jetpack.dataSource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import static com.example.jetpack.dataSource.MyDataSourceFactory.FIRST_PAGE;

/**
 * 适用于 以页的方式请求数据
 */
public class MyPageKeyDataSource extends PageKeyedDataSource<Integer,Movie> {

    private List<Movie> movieList = new ArrayList<>();

    //加载第一页
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer,Movie> callback) {
        callback.onResult(movieList,null,FIRST_PAGE+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer,Movie> callback) {

    }

    //下一页
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer,Movie> callback) {
        Integer nextPage = params.key + 1;
        callback.onResult(movieList,nextPage);
    }
}
