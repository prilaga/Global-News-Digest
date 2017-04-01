package com.sonejka.news.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

@Module
public class FragmentModule {

    private final Activity activity;

    public FragmentModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    Activity activity(){
        return this.activity;
    }

}
