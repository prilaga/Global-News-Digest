package com.mvp.mobexs.mvp_test.presenter;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public interface ILoginPresenter {

    void clear();

    void doLogin(String name, String password);

    void setProgressBarVisibility(int visibility);
}