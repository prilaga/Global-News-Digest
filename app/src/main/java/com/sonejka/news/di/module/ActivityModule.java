package com.sonejka.news.di.module;


import android.app.Activity;

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
