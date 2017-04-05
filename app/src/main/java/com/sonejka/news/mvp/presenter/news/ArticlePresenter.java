package com.sonejka.news.mvp.presenter.news;

import com.sonejka.news.App;
import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.view.fragment.INewsView;
import com.sonejka.news.network.ApiService;
import com.sonejka.news.util.ErrorUtil;
import com.sonejka.news.util.SubscriptionUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class ArticlePresenter implements INewsPresenter<INewsView<Article, Article.Param>, Article.Param> {

    private ApiService mApiService;
    private INewsView<Article, Article.Param> mArticleView;
    private Subscription mSubscription;

    @Inject
    public ArticlePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void setView(INewsView<Article, Article.Param> articleView) {
        mArticleView = articleView;
    }

    @Override
    public void loadData(Article.Param param) {
        unSubscribe();
        mArticleView.onStartLoading();
        mSubscription = SubscriptionUtil.bindObservable(mApiService.getArticles(param), articleObserver);
    }

    private Observer<Article> articleObserver = new Observer<Article>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            String message = ErrorUtil.handleError(e, R.string.error_articles_loading);
            mArticleView.onFailure(message);
        }

        @Override
        public void onNext(Article article) {
            if (article != null)
                mArticleView.updateRecycleView(article);
        }
    };

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
