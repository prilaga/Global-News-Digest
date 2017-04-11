package com.sonejka.news.helper;

import com.sonejka.news.mvp.model.Source;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 11.04.17.
 */

public class SourcesRefreshable extends CachedRefreshable<Source, Source.Param> {

    @Inject
    public SourcesRefreshable() {
    }

    @Override
    public Observable<Source> getSourceObservable(Source.Param parameter) {
        return mApiService.getSources(parameter);
    }

    @Override
    protected void putToCache(Source model, Source.Param parameter) {
        mCacheManager.setSource(model, parameter);
    }

    @Override
    protected Source getFromCache(Source.Param parameter) {
        return mCacheManager.getSource(parameter);
    }

    @Override
    public void clearCache() {
        mCacheManager.clearSources();
    }
}
