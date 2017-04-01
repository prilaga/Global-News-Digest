package com.sonejka.news.mvp.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.sonejka.news.mvp.model.Article;

import javax.inject.Inject;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class ArticleRecyclerAdapter  extends BaseRecyclerAdapter<Article, ArticleRecyclerAdapter.ArticleViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter {

    @Inject
    public ArticleRecyclerAdapter() {
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBindViewHolder(ArticleViewHolder holder, Article article, int position) {
        holder.text.setText(String.format("Item %d", position));
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return String.valueOf(position);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}