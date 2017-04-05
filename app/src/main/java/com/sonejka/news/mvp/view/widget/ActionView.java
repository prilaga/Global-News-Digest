package com.sonejka.news.mvp.view.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.sonejka.news.R;
import com.sonejka.news.util.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Oleg Tarashkevich on 05/04/2017.
 */

public class ActionView extends FrameLayout {

    @BindView(R.id.action_view_button) ImageButton imageButton;
    @Setter @Accessors(prefix = "m") private OnClickListener mOnClickListener;
    private ValueAnimator valueAnimator;

    public ActionView(Context context) {
        this(context, null);
    }

    public ActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ActionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_action, this);
        ButterKnife.bind(this);
    }

    public ActionView setDrawable(@DrawableRes int drawableRes) {
        imageButton.setImageResource(drawableRes);
        return this;
    }

    @OnClick(R.id.action_view_button)
    public void onClick() {

        if (valueAnimator == null) {
            valueAnimator = AnimationUtil.rotate(imageButton, 500L, 90.0F, 0.0F);
        }

        if (!valueAnimator.isRunning())
            valueAnimator.start();

        if (mOnClickListener != null)
            mOnClickListener.onClick(ActionView.this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null)
            valueAnimator.cancel();
    }
}