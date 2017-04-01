package com.sonejka.news.mvp.view.fragment;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface INewsView<N> {

    void onStartLoading();

    void onFailure(String message);

    void updateRecycleView(N news);
}
