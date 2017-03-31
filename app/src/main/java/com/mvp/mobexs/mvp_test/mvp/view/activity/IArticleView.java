package com.mvp.mobexs.mvp_test.mvp.view.activity;

import com.mvp.mobexs.mvp_test.mvp.model.Article;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface IArticleView {

    void onArticleStartLoading();

    void onArticleFailure(String message);

    void onArticleLoaded(Article article);
}
