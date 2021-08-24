package com.example.jetpack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jetpack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "Jetpack";
    private MyViewModel mViewModel;
    private ActivityMainBinding binding;
    User user = new User("tian",18);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        mViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setViewModule(mViewModel);
        binding.setLifecycleOwner(this);//重要!!!
        View view = binding.getRoot();
        setContentView(view);
        init();
        onBindView();
    }

    private void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState");
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG,"onRestoreInstanceState");
    }

    private void onBindView() {
    }
}
