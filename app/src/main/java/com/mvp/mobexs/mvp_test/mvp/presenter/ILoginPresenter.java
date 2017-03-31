package com.mvp.mobexs.mvp_test.mvp.presenter;

import com.mvp.mobexs.mvp_test.mvp.view.activity.ILoginView;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public interface ILoginPresenter {

    void setView(ILoginView loginView);

    void clear();

    void doLogin(String name, String password);

    void setProgressBarVisibility(int visibility);
}