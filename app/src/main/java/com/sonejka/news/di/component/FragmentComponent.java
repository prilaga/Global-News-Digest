package com.sonejka.news.di.component;

import com.sonejka.news.di.annotation.ForFragment;
import com.sonejka.news.di.module.FragmentModule;
import com.sonejka.news.mvp.view.fragment.ArticlesFragment;
import com.sonejka.news.mvp.view.fragment.SourcesFragment;

import dagger.Subcomponent;

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

@ForFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(SourcesFragment fragment);

    void inject(ArticlesFragment fragment);

}
