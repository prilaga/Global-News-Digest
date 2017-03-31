package com.mvp.mobexs.mvp_test.mvp.presenter.news;

import com.mvp.mobexs.mvp_test.mvp.model.RequestParam;
import com.mvp.mobexs.mvp_test.mvp.model.Source;
import com.mvp.mobexs.mvp_test.mvp.view.activity.INewsView;
import com.mvp.mobexs.mvp_test.network.ApiService;
import com.mvp.mobexs.mvp_test.util.SubscriptionUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class SourcePresenter implements INewsPresenter<INewsView<Source>> {

    private ApiService mApiService;
    private INewsView<Source> mNewsView;
    private Subscription mSubscription;

    @Inject
    public SourcePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void setView(INewsView<Source> sourceView) {
        mNewsView = sourceView;
    }

    @Override
    public void loadData() {
        unSubscribe();
        mNewsView.onStartLoading();
        Source.Param param = new Source.Param(RequestParam.Category.BUSINESS, RequestParam.Language.EN, RequestParam.Country.US);
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
            mNewsView.onLoaded(source);
        }
    };

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
