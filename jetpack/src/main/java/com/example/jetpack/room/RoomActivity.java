package com.example.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityRoomBinding;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    ActivityRoomBinding mDataBinding;
    UserViewModel mViewModel;
    RoomAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_room);
        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mDataBinding.setViewModel(mViewModel);
        mAdapter = new RoomAdapter();
        mDataBinding.listView.setAdapter(mAdapter);

        mViewModel.getUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                mAdapter.setUsers(userEntities);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void insertUser(View view) {
        UserEntity entity = new UserEntity("tian",18);
        mViewModel.insertUser(entity);
    }

    public void deleteUser(View view) {
        UserEntity entity2 = new UserEntity(2);
        mViewModel.deleteUser(entity2);
    }

    public void updateUser(View view) {
        UserEntity entity2 = new UserEntity(4,"wenwen",20);
        mViewModel.updateUser(entity2);
    }
}
