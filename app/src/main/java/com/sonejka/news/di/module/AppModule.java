package com.sonejka.news.di.module;

import android.content.Context;

import com.sonejka.news.App;
import com.sonejka.news.di.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Module
public class AppModule {

    private App application;

    public AppModule(App app) {
        application = app;
    }

    @Provides
    @ForApplication
    Context provideContext() {
        return application;
    }

    @Provides
    @ForApplication
    App provideApp() {
        return application;
    }
}
