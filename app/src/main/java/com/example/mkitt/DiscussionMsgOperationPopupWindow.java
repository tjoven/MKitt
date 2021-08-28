package com.example.mkitt;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class DiscussionMsgOperationPopupWindow extends PopupWindow {

    private Context mContext;
    private DiscussionMsgListener quoteListener;

    public void setDiscussionMsgListener(DiscussionMsgListener quoteListener) {
        this.quoteListener = quoteListener;
    }

    public interface DiscussionMsgListener{
        void onQuote();
        void onDelete();
    }
    public DiscussionMsgOperationPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.popup_window_quote, null);
        this.setFocusable(true);// 设置弹出窗口可
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);
        onBindView(layout);
        setContentView(layout);
    }

    private void onBindView(View layout) {
        layout.findViewById(R.id.tv_quote).setOnClickListener((v) ->{ quoteListener.onQuote();});
        layout.findViewById(R.id.tv_delete).setOnClickListener((v) -> { quoteListener.onDelete();});
    }
}
