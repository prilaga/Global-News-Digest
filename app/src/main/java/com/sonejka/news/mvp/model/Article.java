package com.sonejka.news.mvp.model;

import com.sonejka.news.util.DateUtil;

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
        @Getter private String url;
        @Getter private String urlToImage;
        @Getter private Date publishedAt;

        public String getInfo() {
            return author + " | " + getPublishedDate();
        }

        public String getPublishedDate(){
            return DateUtil.dateToString(DateUtil.DateFormat.DATE_FORMAT, publishedAt);
        }
    }

    public static class Param {
        @Getter @Setter private String source;
        @Getter @Setter private String sortBy;

        private Param(String source, @RequestParam.SortBy String sortBy) {
            this.source = source;
            this.sortBy = sortBy;
        }
    }

    public static Param param(){
      return param(null, null);
    }

    public static Param param(String source, @RequestParam.SortBy String sortBy){
       return new Param(source, sortBy);
    }
}
