package com.mvp.mobexs.mvp_test.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.mvp.mobexs.mvp_test.di.annotation.ForActivity;
import com.mvp.mobexs.mvp_test.mvp.model.IUser;
import com.mvp.mobexs.mvp_test.mvp.model.User;
import com.mvp.mobexs.mvp_test.mvp.view.activity.ILoginView;

import javax.inject.Inject;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private IUser iUser;
    private Handler mHandler;

    @Inject
    public LoginPresenter() {
        iUser = new User("mvp", "mvp");
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void setView(ILoginView loginView) {
        iLoginView = loginView;
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        final boolean validation = iUser.validate(name, password);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(validation);
            }
        }, 1000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        iLoginView.onSetProgressBarVisibility(visibility);
    }

}
