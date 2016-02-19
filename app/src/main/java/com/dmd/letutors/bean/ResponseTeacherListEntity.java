package com.dmd.letutors.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ResponseTeacherListEntity {

    private int currentPage;
    private int totalPage;
    private int totalRecord;


    private List<TeacherListEntity> teacherList;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setTeacherList(List<TeacherListEntity> teacherList) {
        this.teacherList = teacherList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public List<TeacherListEntity> getTeacherList() {
        return teacherList;
    }

}
