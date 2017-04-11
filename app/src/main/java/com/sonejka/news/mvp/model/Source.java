package com.sonejka.news.mvp.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class Source {

    @Getter private String status;
    @Getter private List<Entry> sources;

    public static class Entry {
        @Getter private String id;
        @Getter private String name;
        @Getter private String description;
        @Getter private String url;
        @Getter private String category;
        @Getter private String language;
        @Getter private String country;
        @Getter private Logos urlsToLogos;
        @Getter private List<String> sortBysAvailable;

        public String getInfo() {
            return name + " | " + category;
        }
    }

    public static class Logos {
        @Getter private String small;
        @Getter private String medium;
        @Getter private String large;
    }

    @EqualsAndHashCode()
    public static class Param {
        public static final String TAG = "Source.Param";
        @Getter @Setter private String category;
        @Getter @Setter private String language;
        @Getter @Setter private String country;

        private Param(@RequestParam.Category String category, @RequestParam.Language String language, @RequestParam.Country String country) {
            this.category = RequestParam.parameter(category);
            this.language = RequestParam.parameter(language);
            this.country = RequestParam.parameter(country);
        }
    }

    public static Param emptyParam() {
        return param(null, null, null);
    }

    public static Param defaultParam() {
        return param(RequestParam.ALL, RequestParam.ALL, RequestParam.ALL);
    }

    public static Param param(@RequestParam.Category String category, @RequestParam.Language String language, @RequestParam.Country String country) {
        return new Param(category, language, country);
    }

    public static void saveParam(Param param) {

    }
}
