package com.sonejka.news.mvp.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.adapter.MainPagerAdapter;
import com.sonejka.news.mvp.view.adapter.TabItem;
import com.sonejka.news.mvp.view.widget.ActionView;
import com.sonejka.news.mvp.view.widget.NewsTabLayout;
import com.sonejka.news.mvp.view.widget.RequestCardView;
import com.sonejka.news.network.API;
import com.sonejka.news.util.DataUtil;
import com.sonejka.news.util.ShareUtil;
import com.sonejka.news.util.TextUtil;

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
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Inject MainPagerAdapter pagerAdapter;
    @Inject DataUtil dataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        setupToolbar();

        tabLayout.setBackgroundColor(primary);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(primaryLight, Color.WHITE);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        EventBus.getDefault().register(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.subtitle);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.goToWeb(TextUtil.string(R.string.news_source_link));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // region Event Subscriptions
    @Subscribe
    public void onMoveToArticlesEvent(Article.Param param) {
        viewPager.setCurrentItem(TabItem.ARTICLES.ordinal(), true);
    }

    @Subscribe
    public void onMoveToASourcesEvent(Source.Param param) {
        viewPager.setCurrentItem(TabItem.SOURCES.ordinal(), true);
    }
    // endregion

    // region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem apiItem = menu.findItem(R.id.action_settings);

        ActionView actionView = new ActionView(getApplicationContext());
        actionView.setDrawable(R.drawable.ic_settings);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeApiClick(v);
            }
        });
        apiItem.setActionView(actionView);

        return super.onCreateOptionsMenu(menu);
    }

    private void onChangeApiClick(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_api, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.api_news:
                        requestCardView.setApi(API.PRODUCTION);
                        return true;

                    case R.id.api_mock:
                        requestCardView.setApi(API.MOCK);
                        return true;

                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }
    // endregion

    // region Progress bar
    public void showProgress(boolean show){
        if (progressBar != null){
            int visibility = show ? View.VISIBLE : View.GONE;
            progressBar.setVisibility(visibility);
        }
    }
    // endregion
}
