package com.mvp.mobexs.mvp_test.mvp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

public class Article {

    public String status;
    public String sortBy;
    public List<String> articles;

    public static class Entry {
        public String author;
        public String title;
        public String description;
        public String urlToImage;
        public Date publishedAt;
    }

    /*
       {
        "status": "ok",
            "source": "the-next-web",
            "sortBy": "latest",
            -"articles": [
        -{
                "author": "Martijn Scheijbeler",
            "title": "Marketing the TNW Way #19: Site speed, a publishers dilemma",
            "description": "How do we optimize for site speed at TNW? What do we look at, how do we measure improvements and what's next for TNW in measuring our site speed.",
            "url": "https://thenextweb.com/insider/2017/03/29/marketing-tnw-way-19-site-speed-publishers-dilemma/",
            "urlToImage": "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/03/blogs_posts_9_ecommerce-site-speed_2.png",
            "publishedAt": "2017-03-29T11:42:18Z"
},
     */

}
