package com.mvp.mobexs.mvp_test.di.module;

import com.google.gson.Gson;
import com.mvp.mobexs.mvp_test.di.annotation.ForApplication;
import com.mvp.mobexs.mvp_test.network.API;
import com.mvp.mobexs.mvp_test.network.NetworkService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

@Module
public class NetworkModule {

    @Provides
    @ForApplication
    NetworkService provideNetworkService(@Named(GsonModule.IDENTITY) Gson gson, OkHttpClient httpClient) {

        Converter.Factory factory = GsonConverterFactory.create(gson);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient);

        Retrofit retrofit = builder.build();

        return retrofit.create(NetworkService.class);
    }

}
