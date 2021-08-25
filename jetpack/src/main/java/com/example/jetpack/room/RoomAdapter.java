package com.example.jetpack.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.databinding.LayoutItemRoomBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.UserHolder> {

    List<UserEntity> users = new ArrayList<>();

    public RoomAdapter() {
    }

    public RoomAdapter(List<UserEntity> users) {
        this.users = users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemRoomBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_item_room,parent,false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.binding.setUser(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        LayoutItemRoomBinding binding;

        public UserHolder(@NonNull LayoutItemRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
