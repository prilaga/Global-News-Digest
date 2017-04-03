package com.sonejka.news.util;

import android.content.Context;

import com.google.gson.Gson;
import com.sonejka.news.di.annotation.ForApplication;
import com.sonejka.news.di.module.GsonModule;

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
}
