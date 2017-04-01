package com.sonejka.news.di.module;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sonejka.news.di.annotation.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @ForActivity
    FragmentManager providesFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
