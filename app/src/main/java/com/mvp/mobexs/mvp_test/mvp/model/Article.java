package com.mvp.mobexs.mvp_test.mvp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

public class Article {

    public String status;
    public String sortBy;
    public List<Entry> articles;

    public static class Entry {
        public String author;
        public String title;
        public String description;
        public String urlToImage;
        public Date publishedAt;
    }
}
