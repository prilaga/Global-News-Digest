package com.sonejka.news.mvp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Oleg Tarashkevich on 05/04/2017.
 */

public class ShowProgressEvent {

    @Getter @Setter @Accessors(prefix = "m") boolean mShow;

    public ShowProgressEvent(boolean show) {
        mShow = show;
    }
}
