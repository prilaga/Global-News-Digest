package com.sonejka.news.mvp.presenter.request;

import com.sonejka.news.mvp.view.widget.IRequestCardView;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public interface IRequestPresenter {

    void setView(IRequestCardView view);

    void loadRequestParam();

    void startRequestParam();

    void postRequestParam();

    void unSubscribe();
}
