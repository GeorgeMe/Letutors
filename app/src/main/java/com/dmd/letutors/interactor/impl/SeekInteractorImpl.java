package com.dmd.letutors.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.dmd.letutors.bean.ResponseTeacherListEntity;
import com.dmd.letutors.interactor.CommonListInteractor;
import com.dmd.letutors.listeners.BaseMultiLoadedListener;
import com.dmd.letutors.utils.UriHelper;
import com.dmd.letutors.utils.VolleyHelper;
import com.dmd.tutor.utils.TLog;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Administrator on 2016/1/7.
 */
public class SeekInteractorImpl implements CommonListInteractor {

    private BaseMultiLoadedListener<ResponseTeacherListEntity> loadedListener = null;

    public SeekInteractorImpl(BaseMultiLoadedListener<ResponseTeacherListEntity> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonListData(String requestTag,final int event_tag, String keywords, int page) {
        TLog.d(requestTag, UriHelper.getInstance().getSeekUrl(keywords,page));
        TLog.e(requestTag, UriHelper.getInstance().getSeekUrl(keywords,page));
        TLog.i(requestTag, UriHelper.getInstance().getSeekUrl(keywords,page));
        GsonRequest<ResponseTeacherListEntity> gsonRequest = new GsonRequest<ResponseTeacherListEntity>(
                UriHelper.getInstance().getHomeUrl(keywords,page),
                null,
                new TypeToken<ResponseTeacherListEntity>() {
                }.getType(),
                new Response.Listener<ResponseTeacherListEntity>() {
                    @Override
                    public void onResponse(ResponseTeacherListEntity response) {
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
