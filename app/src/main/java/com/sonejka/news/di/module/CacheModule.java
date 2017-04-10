package com.sonejka.news.di.module;

import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.helper.CacheManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 10.04.17.
 */

@Module
public class CacheModule {

    @Provides
    @ForApplication
    public CacheManager providesCacheManager(){
        return new CacheManager();
    }
}
