package com.dmd.letutors.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.dmd.letutors.bean.Home;
import com.dmd.letutors.interactor.CommonListInteractor;
import com.dmd.letutors.listeners.BaseMultiLoadedListener;
import com.dmd.letutors.utils.UriHelper;
import com.dmd.letutors.utils.VolleyHelper;
import com.dmd.tutor.utils.TLog;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Administrator on 2015/12/15.
 */
public class HomeInteracterImpl implements CommonListInteractor {

    private BaseMultiLoadedListener<Home> loadedListener = null;
    public HomeInteracterImpl(BaseMultiLoadedListener<Home> loadedListener){
        this.loadedListener=loadedListener;
    }
    @Override
    public void getCommonListData(String requestTag,final int event_tag, String keywords, int page) {
        TLog.d(requestTag, UriHelper.getInstance().getHomeUrl(keywords,page));
        GsonRequest<Home> gsonRequest = new GsonRequest<Home>(
                UriHelper.getInstance().getHomeUrl(keywords,page),
                null,
                new TypeToken<Home>() {
                }.getType(),
                new Response.Listener<Home>() {
                    @Override
                    public void onResponse(Home response) {
                        loadedListener.onSuccess(event_tag, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loadedListener.onException(error.getMessage());
                    }
                }
        );

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag(requestTag);

        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }
}
