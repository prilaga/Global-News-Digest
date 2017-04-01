package com.sonejka.news.mvp.view.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Oleg Tarashkevich on 09.08.16.
 */
@Accessors(prefix = "m")
public class NewsTabLayout extends TabLayout {

    @Setter private CustomTabLayoutListener mCustomTabLayoutListener;

    public NewsTabLayout(Context context) {
        super(context);
    }

    public NewsTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface CustomTabLayoutListener {
        void onPageSelected(int position);
    }
}
