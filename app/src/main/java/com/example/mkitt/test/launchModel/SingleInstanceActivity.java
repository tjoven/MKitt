package com.example.mkitt.test.launchModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mkitt.R;

/**
 * https://www.cnblogs.com/yongfengnice/p/14636558.html
 * android开发android:taskAffinity标签属性的理解
 *
 * singleTask、singleInstance 可以理解是对taskAffinity的封装
 * standard、singTop下 taskAffinity 无效
 */
public class SingleInstanceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }

    public void jump(View view) {
        Intent intent = new Intent(this,StandardTwoActivity.class);
        startActivity(intent);
    }
}