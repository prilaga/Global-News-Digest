package com.sonejka.news.mvp.model;

import com.sonejka.news.util.DateUtil;
import com.sonejka.news.util.TextUtil;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

@Getter
public class Article {

    private String status;
    private String sortBy;
    private List<Entry> articles;

    @Getter
    public static class Entry {
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private Date publishedAt;

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

    @Data
    @EqualsAndHashCode()
    public static class Param {
        private String source;
        private String sortBy;

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
