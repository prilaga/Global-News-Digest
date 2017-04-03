package com.sonejka.news.util;

import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public final class DateUtil {

    @StringDef({DateFormat.DATE_FORMAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DateFormat {
        String DATE_FORMAT = "MMMM dd yyy, hh:mm:ss";

    }

    public static String dateToString(@DateFormat String format, Date date) {
        String text = "";
        try {
            if (date != null)
                text = android.text.format.DateFormat.format(format, date).toString();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return text;
    }
}
