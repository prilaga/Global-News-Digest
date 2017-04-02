package com.sonejka.news.mvp.presenter.request;

import com.sonejka.news.di.annotation.ForActivity;
import com.sonejka.news.mvp.model.RequestParam;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.widget.IRequestCardView;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

@ForActivity
public class RequestPresenter implements IRequestPresenter{

    private PublishSubject<Void> mPublishSubject = PublishSubject.create();
    private Subscription mSubscription;
    private IRequestCardView mView;

    @Inject
    public RequestPresenter() {
    }

    @Override
    public void setView(IRequestCardView view) {
        mView = view;

        String[] categories = RequestParam.getCategories();
        String[] languages = RequestParam.getLanguages();
        String[] countries = RequestParam.getCountries();

        mView.setCategory(categories, categories[0]);
        mView.setLanguage(languages, languages[0]);
        mView.setCountries(countries, countries[0]);

        unSubscribe();
        mSubscription = mPublishSubject
                .asObservable()
                .debounce(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .onBackpressureLatest()
                .subscribe(requestObserver);

        startRequestParam();
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
        EventBus.getDefault().post(param);
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
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
