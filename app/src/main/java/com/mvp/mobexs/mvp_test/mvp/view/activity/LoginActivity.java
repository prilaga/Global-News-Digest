package com.mvp.mobexs.mvp_test.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvp.mobexs.mvp_test.R;
import com.mvp.mobexs.mvp_test.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    @BindView(R.id.et_login_username) EditText editUser;
    @BindView(R.id.et_login_password) EditText editPass;
    @BindView(R.id.btn_login_login) Button btnLogin;
    @BindView(R.id.btn_login_clear) Button btnClear;
    @BindView(R.id.progress_login) ProgressBar progressBar;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setProgressBarVisibility(View.GONE);
    }

    @Override
    public void onClearText() {
        editUser.setText(null);
        editPass.setText(null);
    }

    @Override
    public void onLoginResult(boolean validate) {
        loginPresenter.setProgressBarVisibility(View.GONE);
        btnLogin.setEnabled(true);
        btnClear.setEnabled(true);
        String message = validate ? "Login Success" : "Login Fail";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @OnClick({R.id.btn_login_login, R.id.btn_login_clear})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_clear:
                loginPresenter.clear();
                break;
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);
                btnClear.setEnabled(false);
                loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
                break;
        }
    }
}
