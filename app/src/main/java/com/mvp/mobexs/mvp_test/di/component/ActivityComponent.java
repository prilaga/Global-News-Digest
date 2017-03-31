package com.mvp.mobexs.mvp_test.di.component;

import com.mvp.mobexs.mvp_test.di.annotation.ForActivity;
import com.mvp.mobexs.mvp_test.di.module.ActivityModule;
import com.mvp.mobexs.mvp_test.di.module.FragmentModule;
import com.mvp.mobexs.mvp_test.mvp.view.activity.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent plus(FragmentModule module);

    void inject(LoginActivity loginActivity);
}
