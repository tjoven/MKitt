package com.example.jetpack.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class MyDataSourceFactory extends DataSource.Factory<Integer,Movie> {

    public static final int PAGE_SIZE = 10;
    public static final int FIRST_PAGE = 1;
    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        return new MyPageKeyDataSource();
    }
}
