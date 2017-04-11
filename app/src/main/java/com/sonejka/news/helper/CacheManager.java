package com.sonejka.news.helper;

import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.util.LimitedSparseArray;

/**
 * Created by Oleg Tarashkevich on 10.04.17.
 */

public class CacheManager {

    private LimitedSparseArray<Article> mArticles;
    private LimitedSparseArray<Source> mSources;

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
            mArticles = new LimitedSparseArray<>(10);
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
            mSources = new LimitedSparseArray<>(10);
        mSources.put(param.hashCode(), source);
    }

    public void clearSources(){
        mSources = null;
    }
    // endregion

}
