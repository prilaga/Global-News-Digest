package com.sonejka.news.util;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public class Logger implements HttpLoggingInterceptor.Logger {

    public static final String TAG = "LOG";

    public static void d(String tag, String message){
        Log.d(tag, message);
    }

    @Override
    public void log(String message) {
      d(TAG, message);
    }
}
