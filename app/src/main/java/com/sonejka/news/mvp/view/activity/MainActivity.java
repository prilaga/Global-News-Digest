package com.sonejka.news.mvp.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.view.adapter.MainPagerAdapter;
import com.sonejka.news.mvp.view.adapter.TabItem;
import com.sonejka.news.mvp.view.widget.NewsTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class MainActivity extends BaseActivity implements NewsTabLayout.CustomTabLayoutListener {

    @BindColor(R.color.primary) int primary;
    @BindColor(R.color.primary_dark) int primaryDark;
    @BindColor(R.color.primary_light) int primaryLight;
    @BindColor(R.color.primary_text) int primaryText;
    @BindColor(R.color.secondary_text) int secondaryText;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.news_tabs) NewsTabLayout tabLayout;
    @BindView(R.id.main_pager_container) ViewPager viewPager;

    @Inject MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        tabLayout.setBackgroundColor(primary);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(primaryLight, Color.WHITE);
        tabLayout.setCustomTabLayoutListener(this);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMoveToArticlesEvent(Article.Param param) {
        viewPager.setCurrentItem(TabItem.ARTICLES.ordinal(), true);
    }

    @Override
    public void onPageSelected(int position) {

    }
}
