package com.mvp.mobexs.mvp_test.di.module;

import android.content.Context;

import com.mvp.mobexs.mvp_test.App;

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
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    App provideApp() {
        return application;
    }
}
