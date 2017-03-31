package com.mvp.mobexs.mvp_test.mvp.presenter;

import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.view.activity.IArticleView;
import com.mvp.mobexs.mvp_test.network.API;
import com.mvp.mobexs.mvp_test.network.NetworkService;
import com.mvp.mobexs.mvp_test.util.SubscriptionUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class ArticlePresenter implements IArticlePresenter {

    private IArticleView mArticleView;
    private NetworkService mNetworkService;
    private Subscription mSubscription;

    @Inject
    public ArticlePresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    public void setView(IArticleView articleView) {
        mArticleView = articleView;
    }

    @Override
    public void loadArticles() {
        unSubscribe();
        mArticleView.onArticleStartLoading();

        Map<String, String> params = new HashMap<>();
        params.put(API.QueryParam.SOURCE, "the-next-web");
        params.put(API.QueryParam.SORT_BY, "latest");
        params.put(API.QueryParam.API_KEY, API.API_KEY);
        mSubscription = SubscriptionUtil.bindObservable(mNetworkService.getArticles(params), articleObserver);
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
