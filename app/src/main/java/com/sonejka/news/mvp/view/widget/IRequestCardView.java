package com.sonejka.news.mvp.view.widget;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public interface IRequestCardView {

    void populateViews(String[] categories, String[] languages, String[] countries);

    String getCategory();

    String getLanguage();

    String getCountry();
}
