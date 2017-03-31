package com.mvp.mobexs.mvp_test.service;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class API {

    @StringDef({QueryParam.SOURCE, QueryParam.SORT_BY, QueryParam.API_KEY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QueryParam {
        String SOURCE = "source";
        String SORT_BY = "sortBy";
        String API_KEY = "apiKey";
    }

    // https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=4c96c741e1444309ad86faafd9774f87
    public final String BASE_URL = "https://newsapi.org";

}
