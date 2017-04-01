package com.sonejka.news.mvp.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.sonejka.news.mvp.model.Source;

import javax.inject.Inject;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourceRecyclerAdapter
        extends BaseRecyclerAdapter<Source.Entry, SourceRecyclerAdapter.SourceViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter {

    @Inject
    public SourceRecyclerAdapter() {
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SourceViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBindViewHolder(SourceViewHolder holder, Source.Entry source, int position) {
        holder.text.setText(source.getDescription());
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return String.valueOf(position);
    }

    static class SourceViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public SourceViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}