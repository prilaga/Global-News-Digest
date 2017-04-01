package com.sonejka.news.mvp.presenter;

import com.sonejka.news.mvp.view.activity.ILoginView;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public interface ILoginPresenter {

    void setView(ILoginView loginView);

    void clear();

    void doLogin(String name, String password);

    void setProgressBarVisibility(int visibility);
}