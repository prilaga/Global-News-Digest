package com.sonejka.news.di.component;

import com.sonejka.news.di.annotation.ForActivity;
import com.sonejka.news.di.module.ActivityModule;
import com.sonejka.news.di.module.FragmentModule;
import com.sonejka.news.mvp.view.activity.MainActivity;
import com.sonejka.news.mvp.view.widget.ArticleCardView;
import com.sonejka.news.mvp.view.widget.SourceCardView;

import dagger.Subcomponent;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

@ForActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent plus(FragmentModule module);

    void inject(MainActivity activity);

    void inject(SourceCardView cardView);

    void inject(ArticleCardView cardView);
}
