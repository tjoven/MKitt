package com.example.mkitt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mkitt.http.Entry;
import com.example.mkitt.http.TestRequest;
import com.example.mkitt.test.TestRetrofitActivity;
import com.example.mkitt.test.rxjava.TestRxJavaActivity;
import com.lenovo.filez.framework.http.base.ApiHelper;
import com.lenovo.filez.framework.http.base.RspListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tzw";

    String extend = "<mark>@@@@";
    String html = "&lt; &gt;&amp;&quot;&copy "+"<html>\n" +
            "<head>\n" +
            "    <meta name=\"viewport\" content=\"user-scalable=no\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"normalize.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id= \"editor\" contentEditable=\"true\" nextimagesuffix=\"0\" lenovoversion=\"1.0\">bdxhjbdxhj<br>&lt;住宿&gt;你最<br>d.x.b<br><br>\n" +
            "\n" +
            "</div>\n" +
            "<script type=\"text/javascript\" src=\"rich_editor.js\"></script>\n" +
            "</body>\n" +
            "</html>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();

            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cancelRequest();
            }
        });

        initHtml();
    }

    private void cancelRequest() {
        ApiHelper.cancelRequestUnderTag(this);
    }

    private void initHtml() {
        String htmlText1 = "1234123412345>qqqqqqqqqqqqqqqqq<b>Hello World</b>";
        final Spanned str1 = Html.fromHtml(htmlText1);

        String htmlText2 = "<font color='#ff0000'>Hello World</font>";
        final Spanned str2 = Html.fromHtml(htmlText2);

        String htmlText3 = "<i><a href='https://www.baidu.com/'>我的博客</a></i>";
        final Spanned str3 = Html.fromHtml(htmlText3);
        extend = TextUtils.htmlEncode(extend);

        final Spanned str = Html.fromHtml(extend+html);

        final TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("html ",extend);
                tv.setText(str);
            }
        });
    }

    private void request() {
        TestRequest request = new TestRequest(MainActivity.this,new RspListener<Entry>(){

            @Override
            public void onRsp(boolean success, Entry obj) {
                Log.d(TAG,"onRsp: "+obj.getData().toString());

            }
        });
        ApiHelper.sendRequest(request);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState Bundle");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG,"onConfigurationChanged");
    }

    public void retrofit(View view) {
        Intent intent = new Intent(this, TestRetrofitActivity.class);
        startActivity(intent);

    }

    public void rxjava(View view) {
        Intent intent = new Intent(this, TestRxJavaActivity.class);
        startActivity(intent);
    }
}
