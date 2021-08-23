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


    private static final String TAG = "Jetpack";
    private MyViewModel dataViewModel;;
    private ActivityMainBinding binding;
    User user = new User("tianzw");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        dataViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setUser(user);
        View view = binding.getRoot();
        setContentView(view);
        init();
        onBindView();
    }

    private void init() {
        dataViewModel.getData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvNumber.setText(integer+"_"+dataViewModel.getExtend());
            }
        });
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
        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setN("jing wen");
                dataViewModel.setExtend("buttonOne");
                dataViewModel.getData().setValue(dataViewModel.getData().getValue()+1);
                Toast.makeText(MainActivity.this,"ActivityMainBinding",Toast.LENGTH_LONG).show();
            }
        });
    }
}
