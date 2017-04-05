package com.sonejka.news.mvp.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.adapter.MainPagerAdapter;
import com.sonejka.news.mvp.view.adapter.TabItem;
import com.sonejka.news.mvp.view.widget.NewsTabLayout;
import com.sonejka.news.mvp.view.widget.RequestCardView;
import com.sonejka.news.network.API;
import com.sonejka.news.util.DataUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class MainActivity extends BaseActivity {

    @BindColor(R.color.primary) int primary;
    @BindColor(R.color.primary_dark) int primaryDark;
    @BindColor(R.color.primary_light) int primaryLight;
    @BindColor(R.color.primary_text) int primaryText;
    @BindColor(R.color.secondary_text) int secondaryText;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.news_tabs) NewsTabLayout tabLayout;
    @BindView(R.id.main_pager_container) ViewPager viewPager;
    @BindView(R.id.request_card_view) RequestCardView requestCardView;

    @Inject MainPagerAdapter pagerAdapter;
    @Inject DataUtil dataUtil;

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

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMoveToArticlesEvent(Article.Param param) {
        viewPager.setCurrentItem(TabItem.ARTICLES.ordinal(), true);
    }

    @Subscribe
    public void onMoveToASourcesEvent(Source.Param param) {
        viewPager.setCurrentItem(TabItem.SOURCES.ordinal(), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                API api = dataUtil.load(API.class, API.TAG_KEY, API.PRODUCTION);
                api = api == API.PRODUCTION ? API.MOCK : API.PRODUCTION;
                requestCardView.setApi(api);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
