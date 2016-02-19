package com.dmd.letutors.presenter;

import com.dmd.letutors.bean.TeacherListEntity;

/**
 * Created by Administrator on 2015/12/22.
 */
public interface SeekPresenter {

    void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh);

    void onItemClickListener(int position, TeacherListEntity itemData);
}
