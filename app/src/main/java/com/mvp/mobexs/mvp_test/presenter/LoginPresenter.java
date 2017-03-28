package com.mvp.mobexs.mvp_test.presenter;

import android.os.Handler;
import android.os.Looper;

import com.mvp.mobexs.mvp_test.model.IUser;
import com.mvp.mobexs.mvp_test.model.User;
import com.mvp.mobexs.mvp_test.view.ILoginView;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private IUser iUser;
    private Handler mHandler;

    public LoginPresenter(ILoginView loginView) {
        iLoginView = loginView;
        iUser = new User("mvp", "mvp");
        mHandler = new Handler(Looper.getMainLooper());
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
