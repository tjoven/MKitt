package com.example.jetpack.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

import com.example.jetpack.R;

public class NaviActivity extends AppCompatActivity {

    private static final String TAG = "NaviActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        NavController navController = Navigation.findNavController(this,R.id.fragment);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed_one");
        super.onBackPressed();
        Log.d(TAG,"onBackPressed");
    }
}
