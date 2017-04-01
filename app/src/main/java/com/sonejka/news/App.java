package com.sonejka.news;

import android.app.Application;
import android.content.Context;

import com.sonejka.news.di.component.AppComponent;
import com.sonejka.news.di.component.DaggerAppComponent;
import com.sonejka.news.di.module.AppModule;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Accessors(prefix = "s")
public class App extends Application {

    @Getter private static Context sContext;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        buildGraph();
    }

    private void buildGraph(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
