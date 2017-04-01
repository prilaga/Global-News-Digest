package com.sonejka.news.di.module;

import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.util.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@Module
public class OkHttpClientModule {

    public static final String API_CLIENT = "API_CLIENT";
    public static final String PICASSO_CLIENT = "PICASSO_CLIENT";

    @Provides @Named(API_CLIENT)
    @ForApplication
    public OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new Logger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HeadersInterceptor())
                .addInterceptor(loggingInterceptor);

        return builder.build();
    }

    @Provides @Named(PICASSO_CLIENT)
    @ForApplication
    public OkHttpClient providePicassoHttpClient(){
        return new OkHttpClient();
    }
}
