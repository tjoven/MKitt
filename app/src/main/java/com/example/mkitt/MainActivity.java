package com.example.mkitt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mkitt.http.Entry;
import com.example.mkitt.http.TestRequest;
import com.example.mkitt.test.TestRetrofitActivity;
import com.lenovo.filez.framework.http.base.ApiHelper;
import com.lenovo.filez.framework.http.base.RspListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

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

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

        initHtml();



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
        Intent intent = new Intent(this, TestRetrofitActivity.class);
        startActivity(intent);

        TestRequest request = new TestRequest(MainActivity.this,new RspListener<Entry>(){

            @Override
            public void onRsp(boolean success, Entry obj) {
                Log.d(TAG,"onRsp: "+obj.getData().toString());

            }
        });
        ApiHelper.sendRequest(request);
    }

}
