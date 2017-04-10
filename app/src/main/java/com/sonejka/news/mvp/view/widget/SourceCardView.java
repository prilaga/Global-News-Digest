package com.sonejka.news.mvp.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.RequestParam;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.activity.BaseActivity;
import com.sonejka.news.util.ListUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourceCardView extends CardView implements View.OnClickListener {

    @BindView(R.id.source_logo_view) ImageView logoImageView;
    @BindView(R.id.source_name_text_view) TextView nameTextView;
    @BindView(R.id.source_description_text_view) TextView descriptionTextView;
    @BindView(R.id.source_url_text_view) TextView urlTextView;

    @BindDimen(R.dimen.z_size) int logoSize;
    @BindColor(R.color.primary_light) int primaryLight;

    @Inject Picasso picasso;
    private Source.Entry mEntry;
    @Setter @Accessors(prefix = "m") private CardSelection mCardSelection;

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
        LayoutInflater.from(getContext()).inflate(R.layout.cardview_source, this);
        ButterKnife.bind(this);

        if (isInEditMode()) return;
        BaseActivity activity = (BaseActivity) getContext();
        activity.getActivityComponent().inject(this);
    }

    public void setSource(Source.Entry entry) {
        mEntry = entry;
        if (entry == null) {
            setOnClickListener(null);
            setVisibility(INVISIBLE);
        } else {
            setOnClickListener(this);
            setVisibility(VISIBLE);

            nameTextView.setText(entry.getInfo());
            descriptionTextView.setText(entry.getDescription());
            urlTextView.setText(entry.getUrl());

            picasso.load(entry.getUrlsToLogos().getMedium())
                    .centerInside()
                    .resize(logoSize, logoSize)
                    .onlyScaleDown()
                    .into(logoImageView);
        }
    }

    public void setSelected(boolean selected){
        int color = selected ? primaryLight : Color.WHITE;
        setCardBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        if (mEntry != null) {

            if (mCardSelection != null)
                mCardSelection.onSelected(mEntry);

            @RequestParam.SortBy String sortBy = ListUtil.getFirst(mEntry.getSortBysAvailable());
            EventBus.getDefault().post(Article.param(mEntry.getId(), sortBy));
        }
    }

    public interface CardSelection{
        void onSelected(Source.Entry selectedEntry);
    }
}
