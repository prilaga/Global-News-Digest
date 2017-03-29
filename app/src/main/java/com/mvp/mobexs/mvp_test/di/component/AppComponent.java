package com.mvp.mobexs.mvp_test.di.component;

import com.mvp.mobexs.mvp_test.di.annotation.ForApplication;
import com.mvp.mobexs.mvp_test.di.module.ActivityModule;
import com.mvp.mobexs.mvp_test.di.module.AppModule;

import dagger.Component;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@ForApplication
@Component(modules = AppModule.class)
public interface AppComponent {

     ActivityComponent plus(ActivityModule module);

}
