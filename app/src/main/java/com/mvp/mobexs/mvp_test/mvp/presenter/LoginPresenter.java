package com.mvp.mobexs.mvp_test.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.mvp.mobexs.mvp_test.mvp.model.IUser;
import com.mvp.mobexs.mvp_test.mvp.model.User;
import com.mvp.mobexs.mvp_test.mvp.view.activity.ILoginView;

import javax.inject.Inject;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;
    private IUser mUser;
    private Handler mHandler;

    @Inject
    public LoginPresenter() {
        mUser = new User("mvp", "mvp");
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void setView(ILoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void clear() {
        mLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        final boolean validation = mUser.validate(name, password);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoginView.onLoginResult(validation);
            }
        }, 1000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        mLoginView.onSetProgressBarVisibility(visibility);
    }

}
