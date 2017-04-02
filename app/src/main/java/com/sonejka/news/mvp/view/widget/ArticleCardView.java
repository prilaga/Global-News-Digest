package com.sonejka.news.mvp.view.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.view.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public class ArticleCardView extends CardView {

    @BindView(R.id.article_logo_view) ImageView logoImageView;
    @BindView(R.id.article_name_text_view) TextView nameTextView;
    @BindView(R.id.article_title_text_view) TextView titleTextView;
    @BindView(R.id.article_url_text_view) TextView urlTextView;
    @BindView(R.id.article_description_text_view) TextView descriptionTextView;

    @Inject Picasso picasso;

    public ArticleCardView(Context context) {
        this(context, null);
    }

    public ArticleCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArticleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.cardview_article, this);
        ButterKnife.bind(this);

        if (isInEditMode()) return;
        BaseActivity activity = (BaseActivity) getContext();
        activity.getActivityComponent().inject(this);
    }

    public void setSource(Article.Entry entry) {
        if (entry == null) {
            setVisibility(INVISIBLE);
        } else {
            setVisibility(VISIBLE);

            nameTextView.setText(entry.getInfo());
            titleTextView.setText(entry.getTitle());
            urlTextView.setText(entry.getUrl());
            descriptionTextView.setText(entry.getDescription());

            picasso.load(entry.getUrlToImage())
                    .into(logoImageView);
        }
    }
}
