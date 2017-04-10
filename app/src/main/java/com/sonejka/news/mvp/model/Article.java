package com.sonejka.news.mvp.model;

import com.sonejka.news.util.DateUtil;
import com.sonejka.news.util.TextUtil;

import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
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
        @Getter private String url;
        @Getter private String urlToImage;
        @Getter private Date publishedAt;

        private String publishedDate = "";

        public String getInfo() {
            return getAuthor() + " | " + getPublishedDate();
        }

        public String getAuthor() {
            return author == null ? "" : author;
        }

        public String getPublishedDate() {
            if (publishedAt != null && TextUtil.isEmpty(publishedDate))
                publishedDate = DateUtil.dateToString(DateUtil.DateFormat.DATE_FORMAT, publishedAt);
            return publishedDate;
        }
    }

    @EqualsAndHashCode()
    public static class Param {
        @Getter @Setter private String source;
        @Getter @Setter private String sortBy;

        private Param(String source, @RequestParam.SortBy String sortBy) {
            this.source = RequestParam.parameter(source);
            this.sortBy = RequestParam.parameter(sortBy);
        }
    }

    public static Param param() {
        return param(null, null);
    }

    public static Param param(String source, @RequestParam.SortBy String sortBy) {
        return new Param(source, sortBy);
    }
}
