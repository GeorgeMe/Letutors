package com.dmd.letutors.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.interactor.TeacherDetailInteractor;
import com.dmd.letutors.listeners.BaseSingleLoadedListener;
import com.dmd.letutors.utils.UriHelper;
import com.dmd.letutors.utils.VolleyHelper;
import com.dmd.tutor.utils.TLog;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Administrator on 2015/12/30.
 */
public class TeacherDetailInteractorImpl implements TeacherDetailInteractor {

    private BaseSingleLoadedListener<TeacherListEntity> loadedListener=null;

    public TeacherDetailInteractorImpl(BaseSingleLoadedListener<TeacherListEntity> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void getTeacherDetail(int id) {
        TLog.e("", UriHelper.getInstance().getTeacherDetail(id));
        GsonRequest<TeacherListEntity> gsonRequest=new GsonRequest<TeacherListEntity>(UriHelper.getInstance().getTeacherDetail(id), null, new TypeToken<TeacherListEntity>() {
        }.getType(), new Response.Listener<TeacherListEntity>() {
            @Override
            public void onResponse(TeacherListEntity response) {
                loadedListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadedListener.onError(error.getMessage());
            }
        });

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag(id);
        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }

    @Override
    public void getTestJson(String json) {
        TLog.e("", UriHelper.getInstance().getTestJson(json));
        GsonRequest<TeacherListEntity> gsonRequest=new GsonRequest<TeacherListEntity>(UriHelper.getInstance().getTestJson(json), null, new TypeToken<TeacherListEntity>() {
        }.getType(), new Response.Listener<TeacherListEntity>() {
            @Override
            public void onResponse(TeacherListEntity response) {
                loadedListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadedListener.onError(error.getMessage());
            }
        });

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag(json);
        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }
}
