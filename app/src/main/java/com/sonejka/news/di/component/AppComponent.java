package com.sonejka.news.di.component;

import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.di.module.ActivityModule;
import com.sonejka.news.di.module.AppModule;
import com.sonejka.news.di.module.CacheModule;
import com.sonejka.news.di.module.DataModule;
import com.sonejka.news.di.module.GsonModule;
import com.sonejka.news.di.module.NetworkModule;
import com.sonejka.news.di.module.OkHttpClientModule;
import com.sonejka.news.mvp.presenter.request.RequestPresenter;

import dagger.Component;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@ForApplication
@Component(modules = {
        AppModule.class,
        GsonModule.class,
        DataModule.class,
        OkHttpClientModule.class,
        NetworkModule.class,
        CacheModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule module);

    void inject(RequestPresenter presenter);

}
