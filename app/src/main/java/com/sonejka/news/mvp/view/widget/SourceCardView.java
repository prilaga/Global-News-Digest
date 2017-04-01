package com.sonejka.news.mvp.view.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.sonejka.news.R;
import com.sonejka.news.mvp.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourceCardView extends CardView {

    @BindView(R.id.name_text_view) TextView nameTextView;
    @BindView(R.id.description_text_view) TextView descriptionTextView;
    @BindView(R.id.url_text_view) TextView urlTextView;

    public SourceCardView(Context context) {
        this(context, null);
    }

    public SourceCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SourceCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.source_cardview, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        BaseActivity activity = (BaseActivity)getContext();
        activity.getActivityComponent().inject(this);
    }
}
