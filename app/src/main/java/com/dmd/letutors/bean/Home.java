package com.dmd.letutors.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class Home {

    private int currentPage;
    private int totalPage;
    private int totalRecord;

    private List<AdvertisementListEntity> advertisementList;

    private List<TeacherListEntity> teacherList;

    private List<SubjectEntity> subjectList;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setAdvertisementList(List<AdvertisementListEntity> advertisementList) {
        this.advertisementList = advertisementList;
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

    public List<AdvertisementListEntity> getAdvertisementList() {
        return advertisementList;
    }

    public List<TeacherListEntity> getTeacherList() {
        return teacherList;
    }

    public void setSubjectList(List<SubjectEntity> subjectList) {
        this.subjectList = subjectList;
    }

    public List<SubjectEntity> getSubjectList() {
        return subjectList;
    }

}
