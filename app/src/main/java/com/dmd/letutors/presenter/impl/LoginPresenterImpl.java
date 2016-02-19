package com.dmd.letutors.presenter.impl;


import com.alibaba.mobileim.YWIMKit;
import com.dmd.letutors.interactor.LoginInteractor;
import com.dmd.letutors.interactor.impl.LoginInteractorImpl;
import com.dmd.letutors.listeners.OnLoginListener;
import com.dmd.letutors.presenter.LoginPresenter;
import com.dmd.letutors.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, OnLoginListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void validateCredentials(YWIMKit mIMKit, String username, String password) {
        loginInteractor.login(mIMKit,username, password, this);
    }

    @Override public void onUsernameError() {
        loginView.setUsernameError();
    }

    @Override public void onPasswordError() {
        loginView.setPasswordError();
    }

    @Override public void onSuccess() {
        loginView.navigateToHome();
    }
}
