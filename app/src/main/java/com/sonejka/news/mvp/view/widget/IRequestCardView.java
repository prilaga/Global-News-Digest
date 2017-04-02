package com.sonejka.news.mvp.view.widget;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public interface IRequestCardView {

    void setCategory(String[] categories, String category);

    void setLanguage(String[] languages, String language);

    void setCountries(String[] countries, String country);

    String getCategory();

    String getLanguage();

    String getCountry();
}
