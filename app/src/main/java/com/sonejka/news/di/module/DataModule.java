package com.sonejka.news.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.util.DataUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 03/04/2017.
 */

@Module
public class DataModule {

    @Provides
    @ForApplication
    public DataUtil providesDataUtil(@ForApplication Context context, @Named(GsonModule.IDENTITY) Gson gson){
        return new DataUtil(context, gson);
    }

}
