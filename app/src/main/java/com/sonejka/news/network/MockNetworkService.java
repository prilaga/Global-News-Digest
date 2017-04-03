package com.sonejka.news.network;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.util.DataUtil;

import java.util.Map;

import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Oleg Tarashkevich on 04.04.17.
 */

public class MockNetworkService implements NetworkService {

    private DataUtil mDataUtil;

    public MockNetworkService(DataUtil dataUtil) {
        mDataUtil = dataUtil;
    }

    @Override
    public Observable<Article> getArticles(@QueryMap Map<String, String> params) {
        return mDataUtil.getResponseFromResObservable(Article.class, R.raw.articles);
    }

    @Override
    public Observable<Source> getSources(@QueryMap Map<String, String> params) {
        return mDataUtil.getResponseFromResObservable(Source.class, R.raw.sources);
    }
}
