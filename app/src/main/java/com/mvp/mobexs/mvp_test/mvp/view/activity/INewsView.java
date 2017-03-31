package com.mvp.mobexs.mvp_test.mvp.view.activity;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface INewsView<T> {

    void onStartLoading();

    void onFailure(String message);

    void onLoaded(T data);
}
