package com.sonejka.news.mvp.presenter.news;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface INewsPresenter<V, P> {

    void setView(V view);

    void loadData(P parameter);

    void unSubscribe();
}
