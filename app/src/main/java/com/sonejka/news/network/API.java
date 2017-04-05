package com.sonejka.news.network;

import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import com.sonejka.news.R;
import com.sonejka.news.util.TextUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.Getter;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public enum API {

    PRODUCTION("https://newsapi.org", R.string.api_news),
    MOCK("https://mock.com", R.string.api_mock);

    public static final String API_KEY = "4c96c741e1444309ad86faafd9774f87";
    public static final String TAG_KEY = "API";

    @Getter private String baseUrl;
    @Getter private String name;

    API(String baseUrl, @StringRes  int name) {
        this.baseUrl = baseUrl;
        this.name = TextUtil.string(name);
    }

    public boolean isMock() {
        return this == MOCK;
    }

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

}
