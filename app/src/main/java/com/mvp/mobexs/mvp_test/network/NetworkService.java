package com.mvp.mobexs.mvp_test.network;

import com.mvp.mobexs.mvp_test.mvp.model.Article;
import com.mvp.mobexs.mvp_test.mvp.model.Source;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface NetworkService {

    String V1 = "/v1";
    String ARTICLES = "/articles";
    String SOURCES = "/sources";

    @GET(V1 + ARTICLES)
    Observable<Article> getArticles(@QueryMap Map<String, String> params);

    @GET(V1 + SOURCES)
    Observable<Source> getSources(@QueryMap Map<String, String> params);
}