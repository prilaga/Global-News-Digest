package com.sonejka.news.mvp.model;

import android.support.annotation.StringDef;

import com.sonejka.news.util.TextUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg Tarashkevich on 31.03.17.
 */

public class RequestParam {

    public static final String ALL = "all";

    @StringDef({ALL, SortBy.TOP, SortBy.LATEST, SortBy.POPULAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortBy {
        String TOP = "top";
        String LATEST = "latest";
        String POPULAR = "popular";
    }

    @StringDef({ALL, Language.EN, Language.DE, Language.FR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Language {
        String EN = "en";
        String DE = "de";
        String FR = "fr";
    }

    @StringDef({ALL, Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Country {
        String AU = "au";
        String DE = "de";
        String GB = "gb";
        String IN = "in";
        String IT = "it";
        String US = "us";
    }

    @StringDef({ALL,
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

    public static @Category String[] getCategories() {
        return new String[]{
                ALL,
                Category.BUSINESS,
                Category.ENTERTAINMENT,
                Category.GAMING,
                Category.GENERAL,
                Category.MUSIC,
                Category.POLITICS,
                Category.SCIENCE_AND_NATURE,
                Category.SPORT,
                Category.TECHNOLOGY};
    }

    public static @Language String[] getLanguages() {
        return new String[]{ALL, Language.EN, Language.DE, Language.FR};
    }

    public static @Country String[] getCountries() {
        return new String[]{ALL, Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US};
    }

    public static String parameter(String parameter){
        return TextUtil.isNotEmpty(parameter) && parameter.equalsIgnoreCase(RequestParam.ALL) ? null : parameter;
    }

    public static String defaultParam(String parameter){
        return TextUtil.isEmpty(parameter) ? RequestParam.ALL : parameter;
    }
}
