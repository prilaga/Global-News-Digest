package com.mvp.mobexs.mvp_test.mvp.presenter;

import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.view.activity.IArticleView;
import com.mvp.mobexs.mvp_test.network.ApiService;
import com.mvp.mobexs.mvp_test.util.SubscriptionUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class ArticlePresenter implements IArticlePresenter {

    private ApiService mApiService;
    private IArticleView mArticleView;
    private Subscription mSubscription;

    @Inject
    public ArticlePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void setView(IArticleView articleView) {
        mArticleView = articleView;
    }

    @Override
    public void loadArticles() {
        unSubscribe();
        mArticleView.onArticleStartLoading();
        mSubscription = SubscriptionUtil.bindObservable(mApiService.getArticles(), articleObserver);
    }

    private Observer<Article> articleObserver = new Observer<Article>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mArticleView.onArticleFailure(e.toString());
        }

        @Override
        public void onNext(Article article) {
            mArticleView.onArticleLoaded(article);
        }
    };

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
