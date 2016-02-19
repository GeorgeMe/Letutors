package com.dmd.letutors.bean;

/**
 * Created by Administrator on 2015/12/28.
 */
public class SubjectEntity {

    /**
     * id : 10
     * imgUrl : http://192.168.1.109:8080/TutorClient/img/subject/home_biol.png
     * subjectName : 体育
     * subjectlevel : {"id":2,"subjectLevelName":"小学"}
     */

    private String id;
    private String imgUrl;
    private String subjectName;
    private SubjectlevelEntity subjectlevel;

    public SubjectEntity(String id, String imgUrl, String subjectName) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.subjectName = subjectName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectlevel(SubjectlevelEntity subjectlevel) {
        this.subjectlevel = subjectlevel;
    }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public SubjectlevelEntity getSubjectlevel() {
        return subjectlevel;
    }

}
