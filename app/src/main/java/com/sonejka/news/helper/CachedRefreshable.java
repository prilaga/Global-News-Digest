package com.sonejka.news.helper;

import com.sonejka.news.network.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Oleg Tarashkevich on 11.04.17.
 *
 * This class helps to get data from network, store it to cache by parameter and retrieve from cache by the same parameter of request
 */

public abstract class CachedRefreshable<M, P> {

    @Inject CacheManager mCacheManager;
    @Inject ApiService mApiService;

    public Observable<M> getObservable(final P param) {
        M model = getFromCache(param);

        if (model == null)
            return getApiServiceObservable(param);
        else
            return Observable.just(model);
    }

    private Observable<M> getApiServiceObservable(final P param) {
        return getSourceObservable(param).flatMap(new Func1<M, Observable<M>>() {
            @Override
            public Observable<M> call(M model) {
                if (model != null)
                    putToCache(model, param);
                return Observable.just(model);
            }
        });
    }

    public abstract Observable<M> getSourceObservable(P parameter);

    protected abstract void putToCache(M model, P parameter);

    protected abstract M getFromCache(P parameter);

    public abstract void clearCache();
}
