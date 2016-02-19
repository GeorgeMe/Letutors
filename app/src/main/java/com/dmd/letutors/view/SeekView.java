package com.dmd.letutors.view;

import com.dmd.letutors.bean.ResponseTeacherListEntity;
import com.dmd.letutors.bean.TeacherListEntity;
import com.dmd.letutors.view.base.BaseView;

/**
 * Created by George on 2015/12/9.
 */
public interface SeekView extends BaseView {

    void navigateToNewsDetail(int position, TeacherListEntity itemData);

    void refreshListData(ResponseTeacherListEntity data);

    void addMoreListData(ResponseTeacherListEntity data);

}
