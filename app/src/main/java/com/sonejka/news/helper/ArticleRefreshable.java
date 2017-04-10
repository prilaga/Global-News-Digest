package com.sonejka.news.helper;

import com.sonejka.news.mvp.model.Article;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 11.04.17.
 */

public class ArticleRefreshable extends CachedRefreshable<Article, Article.Param> {

    @Inject
    public ArticleRefreshable() {
    }

    @Override
    public Observable<Article> getSourceObservable(Article.Param parameter) {
        return mApiService.getArticles(parameter);
    }

    @Override
    protected void putToCache(Article model, Article.Param parameter) {
        mCacheManager.setArticle(model, parameter);
    }

    @Override
    protected Article getFromCache(Article.Param parameter) {
        return mCacheManager.getArticle(parameter);
    }

    @Override
    public void clearCache() {
        mCacheManager.clearArticles();
    }
}
