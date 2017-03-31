package com.mvp.mobexs.mvp_test.mvp.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

public class Article {

    @Getter private String status;
    @Getter private String sortBy;
    @Getter private List<Entry> articles;

    public static class Entry {
        @Getter private String author;
        @Getter private String title;
        @Getter private String description;
        @Getter private String urlToImage;
        @Getter private Date publishedAt;
    }

    public static class Param {
        @Getter @Setter private String source;
        @Getter @Setter private String sortBy;

        public Param(String source, @RequestParam.SortBy String sortBy) {
            this.source = source;
            this.sortBy = sortBy;
        }
    }
}
