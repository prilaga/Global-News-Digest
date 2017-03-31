package com.mvp.mobexs.mvp_test.di.module;


import android.app.Activity;

import com.mvp.mobexs.mvp_test.di.annotation.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){
     this.activity = activity;
    }

    @Provides
    Activity activity(){
        return this.activity;
    }

}
