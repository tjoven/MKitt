package com.example.mkitt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

public class PopupWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
    }

    public void showWindow(View view) {
        DiscussionMsgOperationPopupWindow popupWindow = new DiscussionMsgOperationPopupWindow(view.getContext());
        View popView = popupWindow.getContentView();
        popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int  popupWindowWidth = -popView.getMeasuredWidth();
        int  popupWindowHeight = -popView.getMeasuredHeight();
        Log.d("tzw:popupWindowWidth ",popupWindowWidth+"");
        Log.d("tzw:popupWindowWidth ",popupWindowHeight+"");
        popupWindow.showAsDropDown(view, -popupWindowWidth, 0);
//        popupWindow.showAsDropDown(view, -view.getWidth(),0, Gravity.RIGHT);
    }
}