package com.dmd.letutors.utils;


import com.dmd.letutors.api.ApiConstants;

public class UriHelper {

    private static volatile UriHelper instance = null;

    /**
     * 20 datas per page
     */
    public static final int PAGE_LIMIT = 20;


    private UriHelper() {
    }

    public static UriHelper getInstance() {
        if (null == instance) {
            synchronized (UriHelper.class) {
                if (null == instance) {
                    instance = new UriHelper();
                }
            }
        }
        return instance;
    }
    //页码处理
    public int calculateTotalPages(int totalNumber) {
        if (totalNumber > 0) {
            return totalNumber % PAGE_LIMIT != 0 ? (totalNumber / PAGE_LIMIT + 1) : totalNumber / PAGE_LIMIT;
        } else {
            return 0;
        }
    }
    //登录
    public String getLoginUrl(String phone,String password){
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(ApiConstants.Urls.TUTOR_API_URLS);
        stringbuffer.append("parent_login.action?");
        stringbuffer.append("phone=");
        stringbuffer.append(phone);
        stringbuffer.append("&password=");
        stringbuffer.append(password);
        return stringbuffer.toString().trim();
    }
    //首页
    public String getHomeUrl(String subject,int pageNum){
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(ApiConstants.Urls.TUTOR_API_URLS);
        stringbuffer.append("home_getHomeData.action?");
        stringbuffer.append("subject=");
        stringbuffer.append(subject);
        stringbuffer.append("&page=");
        stringbuffer.append(pageNum);
        return stringbuffer.toString().trim();
    }
    //找老师
    public String getSeekUrl(String subject,int pageNum){
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(ApiConstants.Urls.TUTOR_API_URLS);
        stringbuffer.append("home_getSeekData.action?");
        stringbuffer.append("subject=");
        stringbuffer.append(subject);
        stringbuffer.append("&page=");
        stringbuffer.append(pageNum);
        return stringbuffer.toString().trim();
    }
    //老师详情
    public String getTeacherDetail(int id){
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(ApiConstants.Urls.TUTOR_API_URLS);
        stringbuffer.append("teacher_getTeacherDetail.action?");
        stringbuffer.append("id=");
        stringbuffer.append(id);
        return stringbuffer.toString();
    }

    public String getTestJson(String json){
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(ApiConstants.Urls.TUTOR_API_URLS);
        stringbuffer.append("parent_register.action?");
        stringbuffer.append("json=");
        stringbuffer.append(json);
        return stringbuffer.toString();
    }
}
