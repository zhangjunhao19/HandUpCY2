package com.example.handupcy;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 67698 on 2018/5/25.
 */

public class NoScrollViewPaper extends ViewPager {

    public NoScrollViewPaper (Context context) {
        super(context);
    }
    public NoScrollViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
