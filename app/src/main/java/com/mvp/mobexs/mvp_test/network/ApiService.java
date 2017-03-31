package com.mvp.mobexs.mvp_test.network;

import com.mvp.mobexs.mvp_test.di.annotation.ForApplication;
import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.model.Source;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@ForApplication
public class ApiService {

    @Inject NetworkService mNetworkService;

    @Inject
    public ApiService() {
    }

    public Observable<Article> getArticles(Article.Param param){
        Map<String, String> params = new HashMap<>();
        params.put(API.Query.SOURCE, param.getSource());
        params.put(API.Query.SORT_BY, param.getSortBy());
        params.put(API.Query.API_KEY, API.API_KEY);
        return mNetworkService.getArticles(params);
    }

    public Observable<Source> getSources(Source.Param param){
        Map<String, String> params = new HashMap<>();
        params.put(API.Query.CATEGORY, param.getCategory());
        params.put(API.Query.LANGUAGE, param.getLanguage());
        params.put(API.Query.COUNTRY, param.getCountry());
        params.put(API.Query.API_KEY, API.API_KEY);
        return mNetworkService.getSources(params);
    }
}
