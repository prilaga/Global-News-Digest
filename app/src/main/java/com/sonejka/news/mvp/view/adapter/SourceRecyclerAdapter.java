package com.sonejka.news.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.widget.SourceCardView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourceRecyclerAdapter extends BaseRecyclerAdapter<Source.Entry, SourceRecyclerAdapter.SourceViewHolder> {

    @Inject
    public SourceRecyclerAdapter() {
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SourceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false));
    }

    @Override
    protected void onBindViewHolder(SourceViewHolder holder, Source.Entry entry, int position) {
        holder.cardView.setSource(entry);
    }

    static class SourceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.source_card_view) SourceCardView cardView;

        public SourceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}