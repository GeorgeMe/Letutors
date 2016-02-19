package com.dmd.letutors.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/28.
 */
public class TeacherListEntity implements Serializable {
    private String address;
    private String areaCode;
    private String atitude;
    private String email;
    private String evaluation;
    private String gender;
    private int id;
    private String idcard;
    private String isOpend;
    private String jobType;
    private String longitude;
    private String multPrice;
    private String password;
    private String phone;
    private String simplePrice;
    /**
     * id : 1
     * subjectName : 数学
     * subjectlevel : {"id":1,"subjectLevelName":"幼儿园"}
     */

    private SubjectEntity subject;
    private String teachAddress;
    private String teachWay;
    private String teacherName;
    private String teacherNice;
    private String teacherPic;
    private String totalTeachTime;
    private String zijipinjia;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAtitude() {
        return atitude;
    }

    public void setAtitude(String atitude) {
        this.atitude = atitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIsOpend() {
        return isOpend;
    }

    public void setIsOpend(String isOpend) {
        this.isOpend = isOpend;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMultPrice() {
        return multPrice;
    }

    public void setMultPrice(String multPrice) {
        this.multPrice = multPrice;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSimplePrice() {
        return simplePrice;
    }

    public void setSimplePrice(String simplePrice) {
        this.simplePrice = simplePrice;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public String getTeachAddress() {
        return teachAddress;
    }

    public void setTeachAddress(String teachAddress) {
        this.teachAddress = teachAddress;
    }

    public String getTeachWay() {
        return teachWay;
    }

    public void setTeachWay(String teachWay) {
        this.teachWay = teachWay;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNice() {
        return teacherNice;
    }

    public void setTeacherNice(String teacherNice) {
        this.teacherNice = teacherNice;
    }

    public String getTeacherPic() {
        return teacherPic;
    }

    public void setTeacherPic(String teacherPic) {
        this.teacherPic = teacherPic;
    }

    public String getTotalTeachTime() {
        return totalTeachTime;
    }

    public void setTotalTeachTime(String totalTeachTime) {
        this.totalTeachTime = totalTeachTime;
    }

    public String getZijipinjia() {
        return zijipinjia;
    }

    public void setZijipinjia(String zijipinjia) {
        this.zijipinjia = zijipinjia;
    }

    @Override
    public String toString() {
        return "TeacherListEntity{" +
                "address='" + address + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", atitude='" + atitude + '\'' +
                ", email='" + email + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", idcard='" + idcard + '\'' +
                ", isOpend='" + isOpend + '\'' +
                ", jobType='" + jobType + '\'' +
                ", longitude='" + longitude + '\'' +
                ", multPrice='" + multPrice + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", simplePrice='" + simplePrice + '\'' +
                ", subject=" + subject +
                ", teachAddress='" + teachAddress + '\'' +
                ", teachWay='" + teachWay + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherNice='" + teacherNice + '\'' +
                ", teacherPic='" + teacherPic + '\'' +
                ", totalTeachTime='" + totalTeachTime + '\'' +
                ", zijipinjia='" + zijipinjia + '\'' +
                '}';
    }
}
