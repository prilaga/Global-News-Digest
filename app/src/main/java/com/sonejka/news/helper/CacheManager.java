package com.sonejka.news.helper;

import android.util.SparseArray;

import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;

/**
 * Created by Oleg Tarashkevich on 10.04.17.
 */

public class CacheManager {

    private SparseArray<Article> mArticles;
    private SparseArray<Source> mSources;

    public void reset() {
        clearArticles();
        clearSources();
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

    // region Articles cache
    public Source getSource(Source.Param param) {
        if (mSources == null)
            return null;

        return mSources.get(param.hashCode());
    }

    public void setSource(Source source, Source.Param param) {
        if (mSources == null)
            mSources = new SparseArray<>();
        mSources.put(param.hashCode(), source);
    }

    public void clearSources(){
        mSources = null;
    }
    // endregion

}
