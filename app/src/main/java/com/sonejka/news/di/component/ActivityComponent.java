package com.sonejka.news.di.component;

import com.sonejka.news.di.module.ActivityModule;
import com.sonejka.news.di.module.FragmentModule;
import com.sonejka.news.mvp.view.activity.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent plus(FragmentModule module);

    void inject(LoginActivity loginActivity);
}
