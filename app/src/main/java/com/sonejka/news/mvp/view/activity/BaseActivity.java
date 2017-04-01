package com.sonejka.news.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sonejka.news.App;
import com.sonejka.news.di.component.ActivityComponent;
import com.sonejka.news.di.component.AppComponent;
import com.sonejka.news.di.module.ActivityModule;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link AppComponent}
     */
    protected AppComponent getAppComponent() {
        App app = (App) getApplication();
        return app.getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * Get the Main Activity component for dependency injection.
     *
     * @return {@link ActivityComponent}
     */
    public ActivityComponent getActivityComponent() {
        if (activityComponent == null)
            activityComponent = getAppComponent().plus(getActivityModule());
        return activityComponent;
    }
}
