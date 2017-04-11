package com.sonejka.news.mvp.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@Getter
public class Source {

    private String status;
    private List<Entry> sources;

    @Getter
    public static class Entry {
        private String id;
        private String name;
        private String description;
        private String url;
        private String category;
        private String language;
        private String country;
        private Logos urlsToLogos;
        private List<String> sortBysAvailable;

        public String getInfo() {
            return name + " | " + category;
        }
    }

    @Getter
    public static class Logos {
        private String small;
        private String medium;
        private String large;
    }

    @Data
    @EqualsAndHashCode()
    public static class Param {
        public static final String TAG = "Source.Param";
        private String category;
        private String language;
        private String country;

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
