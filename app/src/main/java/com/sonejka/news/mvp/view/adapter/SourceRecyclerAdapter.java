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

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourceRecyclerAdapter extends RecyclerView.Adapter<SourceRecyclerAdapter.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(String.format("Item %d", position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return String.valueOf(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}