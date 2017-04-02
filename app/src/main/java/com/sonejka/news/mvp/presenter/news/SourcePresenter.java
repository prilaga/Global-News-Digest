package com.sonejka.news.mvp.presenter.news;

import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.fragment.INewsView;
import com.sonejka.news.network.ApiService;
import com.sonejka.news.util.SubscriptionUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class SourcePresenter implements INewsPresenter<INewsView<Source, Source.Param>, Source.Param> {

    private ApiService mApiService;
    private INewsView<Source, Source.Param> mNewsView;
    private Subscription mSubscription;

    @Inject
    public SourcePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void setView(INewsView<Source, Source.Param> sourceView) {
        mNewsView = sourceView;
    }

    @Override
    public void loadData(Source.Param param) {
        unSubscribe();
        mNewsView.onStartLoading();
        mSubscription = SubscriptionUtil.bindObservable(mApiService.getSources(param), sourceObserver);
    }

    private Observer<Source> sourceObserver = new Observer<Source>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mNewsView.onFailure(e.toString());
        }

        @Override
        public void onNext(Source source) {
            if (source != null)
                mNewsView.updateRecycleView(source);
        }
    };

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
