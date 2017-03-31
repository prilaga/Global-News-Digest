package com.mvp.mobexs.mvp_test.network;

import com.mvp.mobexs.mvp_test.di.annotation.ForApplication;
import com.mvp.mobexs.mvp_test.mvp.model.Article;

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

    public Observable<Article> getArticles(){
        Map<String, String> params = new HashMap<>();
        params.put(API.QueryParam.SOURCE, "the-next-web");
        params.put(API.QueryParam.SORT_BY, "latest");
        params.put(API.QueryParam.API_KEY, API.API_KEY);
        return mNetworkService.getArticles(params);
    }
}
