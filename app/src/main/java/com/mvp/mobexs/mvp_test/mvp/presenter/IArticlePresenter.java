package com.mvp.mobexs.mvp_test.mvp.presenter;

import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.view.activity.IArticleView;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface IArticlePresenter {

    void setView(IArticleView articleView);

    void loadArticles();

    void unSubscribe();
}
