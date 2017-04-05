package com.sonejka.news.network;

import android.text.TextUtils;

import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@ForApplication
public class ApiService {

    @Inject NetworkServicesContainer mServicesContainer;

    @Inject
    public ApiService() {
    }

    public void setApi(API api){
        mServicesContainer.setApi(api);
    }

    public Observable<Article> getArticles(Article.Param param) {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(param.getSource()))
            params.put(API.Query.SOURCE, param.getSource());
        if (!TextUtils.isEmpty(param.getSortBy()))
            params.put(API.Query.SORT_BY, param.getSortBy());
        return mServicesContainer.get().getArticles(params);
    }

    public Observable<Source> getSources(Source.Param param) {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(param.getCategory()))
            params.put(API.Query.CATEGORY, param.getCategory());
        if (!TextUtils.isEmpty(param.getLanguage()))
            params.put(API.Query.LANGUAGE, param.getLanguage());
        if (!TextUtils.isEmpty(param.getCountry()))
            params.put(API.Query.COUNTRY, param.getCountry());
        return mServicesContainer.get().getSources(params);
    }
}
