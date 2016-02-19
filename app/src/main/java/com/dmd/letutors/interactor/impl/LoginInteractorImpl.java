package com.dmd.letutors.interactor.impl;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.dmd.letutors.bean.Msg;
import com.dmd.letutors.interactor.LoginInteractor;
import com.dmd.letutors.listeners.OnLoginListener;
import com.dmd.letutors.utils.UriHelper;
import com.dmd.letutors.utils.VolleyHelper;
import com.dmd.tutor.utils.TLog;
import com.google.gson.reflect.TypeToken;


public class LoginInteractorImpl implements LoginInteractor {
    private String  TAG=LoginInteractorImpl.class.getSimpleName();

    @Override
    public void login(final YWIMKit mIMKit,final String username, final String password,final OnLoginListener listener) {
        final String url=UriHelper.getInstance().getLoginUrl(username,password);
        GsonRequest<Msg> gsonRequest=new GsonRequest<Msg>(UriHelper.getInstance().getLoginUrl(username,password),null,
                new TypeToken<Msg>() {
                }.getType(),new Response.Listener<Msg>(){
            @Override
            public void onResponse(Msg response) {
                TLog.e(TAG,url+"  "+response.getMsg());
                if (1==Integer.parseInt(response.getStatus())){
                    loginOpenIm(mIMKit,username, password,listener);
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag("login");

        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }
    private void loginOpenIm(YWIMKit mIMKit,String username,String password, final OnLoginListener listener){
        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(username, password);
        if (loginParam!=null&&loginService!=null){
            loginService.login(loginParam, new IWxCallback() {

                @Override
                public void onSuccess(Object... arg0) {
                    //登录成功
                    listener.onSuccess();
                }

                @Override
                public void onProgress(int arg0) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onError(int errCode, String description) {
                    //如果登录失败，errCode为错误码,description是错误的具体描述信息
                    if (errCode==1){
                        listener.onPasswordError();
                    }else if (errCode==2){
                        listener.onUsernameError();
                    }
                }
            });
        }else {
            //登录失败
        }
    }
}
