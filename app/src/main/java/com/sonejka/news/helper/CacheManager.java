package com.sonejka.news.helper;

import android.util.SparseArray;

import com.sonejka.news.mvp.model.Article;

/**
 * Created by Oleg Tarashkevich on 10.04.17.
 */

public class CacheManager {

    private SparseArray<Article> mArticles;

    public void reset() {
        clearArticles();
    }

    // region Articles cache
    public Article getArticle(Article.Param param) {
        if (mArticles == null)
            return null;

        return mArticles.get(param.hashCode());
    }

    public void setArticle(Article article, Article.Param param) {
        if (mArticles == null)
            mArticles = new SparseArray<>();
        mArticles.put(param.hashCode(), article);
    }

    public void clearArticles(){
        mArticles = null;
    }
    // endregion

}
