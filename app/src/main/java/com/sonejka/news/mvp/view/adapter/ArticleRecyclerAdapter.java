package com.sonejka.news.mvp.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.view.widget.ArticleCardView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class ArticleRecyclerAdapter
        extends BaseRecyclerAdapter<Article.Entry, ArticleRecyclerAdapter.ArticleViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter {

    @Inject
    public ArticleRecyclerAdapter() {
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleRecyclerAdapter.ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(ArticleViewHolder holder, Article.Entry entry, int position) {
        holder.cardView.setSource(entry);
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return getItem(position).getPublishedDate();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_card_view) ArticleCardView cardView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}