package com.mvp.mobexs.mvp_test.network;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class API {

    @StringDef({Query.SOURCE, Query.SORT_BY, Query.API_KEY, Query.CATEGORY, Query.LANGUAGE, Query.COUNTRY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Query {
        String SOURCE = "source";
        String SORT_BY = "sortBy";
        String API_KEY = "apiKey";
        String CATEGORY = "category";
        String LANGUAGE = "language";
        String COUNTRY = "country";
    }

    public static final String API_KEY = "4c96c741e1444309ad86faafd9774f87";
    public static final String BASE_URL = "https://newsapi.org";

}
