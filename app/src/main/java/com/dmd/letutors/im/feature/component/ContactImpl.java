package com.dmd.letutors.im.feature.component;

import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.YWOnlineContact;

/**
 * Created by Administrator on 2016/1/4.
 */
public class ContactImpl  implements IYWContact {
    private String userid = "", appKey = "", avatarPath = "", showName = "";
    private int status= YWOnlineContact.ONLINESTATUS_ONLINE;

    public ContactImpl(String showName, String userid, String avatarPath, String signatures, String appKey) {
        this.showName = showName;
        this.userid = userid;
        this.avatarPath = avatarPath;
        this.appKey = appKey;
    }
    public void setOnlineStatus(int status){
        this.status=status;
    }
    @Override
    public String getUserId() {
        return userid;
    }

    @Override
    public String getAppKey() {
        return appKey;
    }

    @Override
    public String getAvatarPath() {
        return avatarPath;
    }

    @Override
    public String getShowName() {
        return showName;
    }

    public int getOnlineStatus() {
        return status;
    }
}
