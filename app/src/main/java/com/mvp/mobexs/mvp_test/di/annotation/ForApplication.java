package com.mvp.mobexs.mvp_test.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Oleg Tarashkevich on 28/03/2017.
 */

/**
 * A scoping annotation to permit objects during application life circle
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
