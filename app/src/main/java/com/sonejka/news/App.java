package com.sonejka.news;

import android.app.Application;

import com.sonejka.news.di.component.AppComponent;
import com.sonejka.news.di.component.DaggerAppComponent;
import com.sonejka.news.di.module.AppModule;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        buildGraph();
    }

    private void buildGraph(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
