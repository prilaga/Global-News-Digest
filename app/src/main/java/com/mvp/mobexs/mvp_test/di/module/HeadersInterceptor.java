package com.mvp.mobexs.mvp_test.di.module;

import com.mvp.mobexs.mvp_test.network.API;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .header("X-Api-Key", API.API_KEY);
        return chain.proceed(builder.build());
    }
}
