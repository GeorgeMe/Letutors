package com.dmd.letutors.interactor;


import com.alibaba.mobileim.YWIMKit;
import com.dmd.letutors.listeners.OnLoginListener;

public interface LoginInteractor {
    public void login(YWIMKit mIMKit,String username, String password, OnLoginListener listener);
}
