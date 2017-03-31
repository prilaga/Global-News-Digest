package com.mvp.mobexs.mvp_test.mvp.presenter.news;

import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.model.RequestParam;
import com.mvp.mobexs.mvp_test.mvp.view.activity.INewsView;
import com.mvp.mobexs.mvp_test.network.ApiService;
import com.mvp.mobexs.mvp_test.util.SubscriptionUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class ArticlePresenter implements INewsPresenter<INewsView<Article>> {

    private ApiService mApiService;
    private INewsView<Article> mArticleView;
    private Subscription mSubscription;

    @Inject
    public ArticlePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void setView(INewsView<Article> articleView) {
        mArticleView = articleView;
    }

    @Override
    public void loadData() {
        unSubscribe();
        mArticleView.onStartLoading();
        Article.Param param = new Article.Param("the-next-web", RequestParam.SortBy.LATEST);
        mSubscription = SubscriptionUtil.bindObservable(mApiService.getArticles(param), articleObserver);
    }

    private Observer<Article> articleObserver = new Observer<Article>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mArticleView.onFailure(e.toString());
        }

        @Override
        public void onNext(Article article) {
            mArticleView.onLoaded(article);
        }
    };

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
