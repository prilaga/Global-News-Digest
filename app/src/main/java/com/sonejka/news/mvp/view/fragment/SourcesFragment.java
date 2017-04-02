package com.sonejka.news.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.sonejka.news.R;
import com.sonejka.news.mvp.model.RequestParam;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.presenter.news.SourcePresenter;
import com.sonejka.news.mvp.view.adapter.SourceRecyclerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourcesFragment extends BaseFragment implements INewsView<Source, Source.Param> {

    @BindView(R.id.recycler_view) FastScrollRecyclerView recyclerView;

    @Inject SourcePresenter sourcePresenter;
    @Inject SourceRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);
        getFragmentComponent().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sourcePresenter.setView(this);
        onDataLoadEvent(Source.param());
//        onDataLoadEvent(Source.param(RequestParam.Category.BUSINESS, RequestParam.Language.EN, RequestParam.Country.US));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    // region INewsView
    @Subscribe
    @Override
    public void onDataLoadEvent(Source.Param param) {
        sourcePresenter.loadData(param);
    }

    @Override
    public void onStartLoading() {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void updateRecycleView(Source source) {
        adapter.setData(source.getSources());
    }
    // endregion
}
