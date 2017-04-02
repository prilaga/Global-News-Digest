package com.sonejka.news.mvp.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg Tarashkevich on 31.03.17.
 */

public class RequestParam {

    @StringDef({SortBy.DEFAULT, SortBy.TOP, SortBy.LATEST, SortBy.POPULAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortBy {
        String DEFAULT = "default";
        String TOP = "top";
        String LATEST = "latest";
        String POPULAR = "popular";
    }

    @StringDef({Language.DEFAULT, Language.EN, Language.DE, Language.GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Language {
        String DEFAULT = "default";
        String EN = "en";
        String DE = "de";
        String GB = "fr";
    }

    @StringDef({Country.DEFAULT, Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Country {
        String DEFAULT = "default";
        String AU = "au";
        String DE = "de";
        String GB = "gb";
        String IN = "in";
        String IT = "it";
        String US = "us";
    }

    @StringDef({
            Category.DEFAULT,
            Category.BUSINESS,
            Category.ENTERTAINMENT,
            Category.GAMING,
            Category.GENERAL,
            Category.MUSIC,
            Category.POLITICS,
            Category.SCIENCE_AND_NATURE,
            Category.SPORT,
            Category.TECHNOLOGY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Category {
        String DEFAULT = "default";
        String BUSINESS = "business";
        String ENTERTAINMENT = "entertainment";
        String GAMING = "gaming";
        String GENERAL = "general";
        String MUSIC = "music";
        String POLITICS = "politics";
        String SCIENCE_AND_NATURE = "science-and-nature";
        String SPORT = "sport";
        String TECHNOLOGY = "technology";
    }

}
