package com.mvp.mobexs.mvp_test.mvp.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class Source {

    @StringDef({HeadLine.TOP, HeadLine.LATEST, HeadLine.POPULAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HeadLine {
        String TOP = "top";
        String LATEST = "latest";
        String POPULAR = "popular";
    }

    @StringDef({Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Country {
        String AU = "au";
        String DE = "de";
        String GB = "gb";
        String IN = "in";
        String IT = "it";
        String US = "us";
    }

    public String status;

    public static class Entry {
        public String id;
        public String name;
        public String description;
        public String url;
        public String category;
        public String language;
        public String country;
        public Logos urlsToLogos;
        public List<String> sortBysAvailable;
    }

    public static class Logos {
        public String small;
        public String medium;
        public String large;
    }

}
