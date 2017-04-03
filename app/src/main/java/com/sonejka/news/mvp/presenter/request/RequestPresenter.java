package com.sonejka.news.mvp.presenter.request;

import com.sonejka.news.di.annotation.ForActivity;
import com.sonejka.news.mvp.model.RequestParam;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.widget.IRequestCardView;
import com.sonejka.news.util.DataUtil;
import com.sonejka.news.util.SubscriptionUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

@ForActivity
public class RequestPresenter implements IRequestPresenter {

    @Inject DataUtil mDataUtil;

    private PublishSubject<Void> mPublishSubject = PublishSubject.create();
    private Subscription mSubscription;
    private IRequestCardView mView;

    @Inject
    public RequestPresenter() {
    }

    @Override
    public void setView(IRequestCardView view) {
        mView = view;

        unSubscribe();
        mSubscription = mPublishSubject
                .asObservable()
                .debounce(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .onBackpressureLatest()
                .subscribe(requestObserver);

        loadParamData();
    }

    @Override
    public void startRequestParam() {
        mPublishSubject.onNext(null);
    }

    @Override
    public void postRequestParam() {
        @RequestParam.Category String category = mView.getCategory();
        @RequestParam.Language String language = mView.getLanguage();
        @RequestParam.Country String country = mView.getCountry();
        Source.Param param = Source.param(category, language, country);
        saveParamData(param);
        EventBus.getDefault().post(param);
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    // region Load Source.Param
    private void loadParamData() {
        Observable<Source.Param> observable = mDataUtil.loadAsync(Source.Param.class, Source.Param.TAG);
        SubscriptionUtil.bindObservable(observable, paramObserver);
    }

    private Observer<Source.Param> paramObserver = new Observer<Source.Param>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Source.Param param) {
            loadParam(param);
        }
    };

    private void loadParam(Source.Param param) {
        if (param == null)
            param = Source.defaultParam();

        String[] categories = RequestParam.getCategories();
        String[] languages = RequestParam.getLanguages();
        String[] countries = RequestParam.getCountries();

        mView.setCategory(categories, RequestParam.defaultParam(param.getCategory()));
        mView.setLanguage(languages, RequestParam.defaultParam(param.getLanguage()));
        mView.setCountries(countries, RequestParam.defaultParam(param.getCountry()));

        EventBus.getDefault().post(param);
    }
    // endregion

    private void saveParamData(Source.Param param) {
        if (param != null)
            mDataUtil.save(param, Source.Param.TAG);
    }

    private Observer<Void> requestObserver = new Observer<Void>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Void aVoid) {
            postRequestParam();
        }
    };


}
