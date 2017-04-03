package com.sonejka.news.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.google.gson.Gson;
import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.di.module.GsonModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.inject.Named;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Oleg Tarashkevich on 03/04/2017.
 */

public final class DataUtil {

    private TinyDB mTinyDB;
    private Gson mGson;

    public DataUtil(Context context, Gson gson) {
        mTinyDB = new TinyDB(context);
        mGson = gson;
    }

    /**
     * Serialize & Deserialize
     */

    public String serialize(Object object) {
        String json = mGson.toJson(object);
        return json;
    }

    public <L> L deserialize(String json, Class<L> tClass) {
        L object = mGson.fromJson(json, tClass);
        return object;
    }

    /**
     * Save & Load object
     */

    public void save(Object object, String key) {
        String json = serialize(object);
        mTinyDB.putString(key, json);
    }

    public <L> L load(Class<L> tClass, String key) {
        String json = mTinyDB.getString(key, "");
        L object = deserialize(json, tClass);
        return object;
    }

    public <L> Observable<L> loadAsync(final Class<L> tClass, final String key) {
        return Observable.create(new Observable.OnSubscribe<L>() {
            @Override
            public void call(Subscriber<? super L> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        L object = load(tClass, key);
                        subscriber.onNext(object);
                        subscriber.onCompleted();
                    }
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @NonNull
    private Observable<String> readStringFromResObservable(final Context context, @RawRes final int rawId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;

                try {
                    String jsonString = readJsonFromRes();
                    subscriber.onNext(jsonString);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }

            private String readJsonFromRes() throws IOException {
                InputStream is = context.getResources().openRawResource(rawId);
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    is.close();
                }

                return writer.toString();
            }
        });
    }
}
