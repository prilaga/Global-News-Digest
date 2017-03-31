package com.mvp.mobexs.mvp_test.mvp.presenter.news;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface INewsPresenter<V> {

    void setView(V view);

    void loadData();

    void unSubscribe();
}
