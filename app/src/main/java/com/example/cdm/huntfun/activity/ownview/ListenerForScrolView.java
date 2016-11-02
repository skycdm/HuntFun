package com.example.cdm.huntfun.activity.ownview;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by cdm on 2016/10/27.
 */
public class ListenerForScrolView extends ScrollView {

    public ListenerForScrolView(Context context) {
        super(context);
    }

    public ListenerForScrolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenerForScrolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }

    private OnScrollChangedListener mOnScrollChangedListener;


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }
}
