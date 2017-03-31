package com.mvp.mobexs.mvp_test.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@Module
public class GsonModule {

    public static final String IDENTITY = "IDENTITY";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Provides
    @Singleton
    @Named(IDENTITY)
    public Gson provideIdentityGson() {
        return buildGson(FieldNamingPolicy.IDENTITY);
    }

    private Gson buildGson(FieldNamingPolicy policy) {
        return new GsonBuilder()
                .setFieldNamingPolicy(policy)
                .setDateFormat(DATE_FORMAT)
                .create();
    }
}
