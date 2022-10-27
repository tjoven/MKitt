package com.example.mkitt.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mkitt.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("tzw","onWindowFocusChanged");
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("tzw","onAttachedToWindow");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tzw","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("tzw","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tzw","onResume");

        isDialogShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tzw","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tzw","onStop");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.d("tzw","onUserInteraction");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d("tzw","onUserLeaveHint");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.d("tzw","onCreateDialog");
        return super.onCreateDialog(id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void showDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog dialog=builder.setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }

    public void dialog(View view) {
        showDialog();
    }

    private void isDialogShow() {
        View decorViewViewActivity = getWindow().getDecorView();
        ArrayList<View> views = getWindowViews();
        for (View view : views) {
            Log.d("tzw", view.getWindowId() + "");
            if (decorViewViewActivity != view) {
                Log.d("tzw","isDialogShow = "+ true);
            }
        }
    }

    public static ArrayList<View> getWindowViews() {
        try {
            View rootView = null;
            Class wmgClass = Class.forName("android.view.WindowManagerGlobal");
            Object wmgInstnace = wmgClass.getMethod("getInstance").invoke(null, (Object[]) null);
            Field mViewsField = wmgClass.getDeclaredField("mViews");
            mViewsField.setAccessible(true);
            ArrayList o = (ArrayList) mViewsField.get(wmgInstnace);
            return o;

//            private final ArrayList<View> mViews = new ArrayList<View>();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}