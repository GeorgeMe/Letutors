package com.dmd.letutors.view;

import com.dmd.letutors.bean.TeacherListEntity;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface TeacherDetailView{
    /**
     * show error message
     */
    public void TeacherDetailshowError(String msg);

    /**
     * show exception message
     */
    public void TeacherDetailshowException(String msg);
    public void setTeacherDetail(TeacherListEntity data);

    public void navigateToHome();

}
