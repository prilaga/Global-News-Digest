package com.mvp.mobexs.mvp_test.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mvp.mobexs.mvp_test.App;
import com.mvp.mobexs.mvp_test.di.component.ActivityComponent;
import com.mvp.mobexs.mvp_test.di.component.AppComponent;
import com.mvp.mobexs.mvp_test.di.module.ActivityModule;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponent();
    }

    protected void setComponent(){
        activityComponent = getActivityComponent();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link AppComponent}
     */
    protected AppComponent getAppComponent(){
        App app = (App) getApplication();
        return app.getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    /**
     * Get the Main Activity component for dependency injection.
     *
     * @return {@link ActivityComponent}
     */
    protected ActivityComponent getActivityComponent(){
        return getAppComponent().plus(getActivityModule());
    }
}
