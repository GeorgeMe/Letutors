package com.dmd.letutors.presenter;

import com.alibaba.mobileim.YWIMKit;

public interface LoginPresenter {
    public void validateCredentials(YWIMKit mIMKit, String username, String password);
}
