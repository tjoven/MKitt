package com.example.jetpack.dataSource;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import static com.example.jetpack.dataSource.MyDataSourceFactory.PAGE_SIZE;

public class MyViewModel extends ViewModel {

    private LiveData<PagedList<Movie>> mMovieList;

    public MyViewModel() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(2)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build();
        mMovieList = new LivePagedListBuilder<Integer,Movie>(new MyDataSourceFactory(),config)
                .build();
    }
}
