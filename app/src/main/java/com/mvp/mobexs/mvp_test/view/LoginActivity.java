package com.mvp.mobexs.mvp_test.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvp.mobexs.mvp_test.R;
import com.mvp.mobexs.mvp_test.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button btnClear;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //find view
        editUser = (EditText) this.findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        btnClear = (Button) this.findViewById(R.id.btn_login_clear);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);

        //set listener
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);

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

    @Override
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
