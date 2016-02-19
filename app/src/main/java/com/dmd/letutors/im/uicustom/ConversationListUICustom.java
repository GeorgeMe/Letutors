package com.dmd.letutors.im.uicustom;

import android.support.v4.app.Fragment;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;

/**
 * Created by Administrator on 2016/1/4.
 */
public class ConversationListUICustom  extends IMConversationListUI {
    public ConversationListUICustom(Pointcut pointcut) {
        super(pointcut);
    }

    @Override
    public boolean needHideTitleView(Fragment fragment) {
        return true;
    }
}
