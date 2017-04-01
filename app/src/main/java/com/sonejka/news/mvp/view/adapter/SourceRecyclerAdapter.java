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
import com.sonejka.news.mvp.view.widget.SourceCardView;

import javax.inject.Inject;

import lombok.Getter;
import lombok.experimental.Accessors;

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
        return new SourceViewHolder(new SourceCardView(parent.getContext()));
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBindViewHolder(SourceViewHolder holder, Source.Entry entry, int position) {
        holder.getSourceCardView().setSource(entry);
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return String.valueOf(position);
    }

    static class SourceViewHolder extends RecyclerView.ViewHolder {
        @Getter @Accessors(prefix = "m") SourceCardView mSourceCardView;

        SourceViewHolder(SourceCardView itemView) {
            super(itemView);
            mSourceCardView = itemView;
        }
    }
}