package com.example.jetpack.bindingAdapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class TextViewBindingAdapter {

    @BindingAdapter("text")
    public static void setTextView(TextView textView,String string){
        textView.setText(string);
    }
}
