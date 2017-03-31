package com.mvp.mobexs.mvp_test.di.module;

import com.mvp.mobexs.mvp_test.util.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@Module
public class OkHttpClientModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new Logger());

        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        return builder.build();
    }
}
