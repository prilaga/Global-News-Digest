package com.sonejka.news.network;

import com.google.gson.Gson;
import com.sonejka.news.util.DataUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg Tarashkevich on 05/04/2017.
 */

public final class NetworkServicesContainer {

    private Map<API, NetworkService> mNetworkServices = new HashMap<>(API.values().length);

    private API mApi;
    private Gson mGson;
    private OkHttpClient mOkHttpClient;
    private DataUtil mDataUtil;

    @Inject
    public NetworkServicesContainer(Gson mGson, OkHttpClient mOkHttpClient, DataUtil mDataUtil) {
        this.mGson = mGson;
        this.mOkHttpClient = mOkHttpClient;
        this.mDataUtil = mDataUtil;
    }

    public NetworkService get() {
        NetworkService service = mNetworkServices.get(getApi());
        if (service == null) {
            if (getApi().isMock())
                service = new MockNetworkService(mDataUtil);
            else
                service = createLiveService();
            mNetworkServices.put(getApi(), service);
        }
        return service;
    }

    public API getApi() {
        if (mApi == null)
            mApi = mDataUtil.load(API.class, API.TAG_KEY, API.PRODUCTION);
        return mApi;
    }

    public API setApi(API api) {
        if (api != null) {
            mApi = api;
            mDataUtil.save(mApi, API.TAG_KEY);
        }
        return mApi;
    }

    private NetworkService createLiveService() {
        Converter.Factory factory = GsonConverterFactory.create(mGson);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getApi().getBaseUrl())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(mOkHttpClient);

        Retrofit retrofit = builder.build();
        return retrofit.create(NetworkService.class);
    }
}
