package com.dmd.letutors.bean;

/**
 * Created by Administrator on 2015/12/28.
 */
public class AdvertisementListEntity {

    private String advertisementLink;
    private String advertisementTitle;
    private String advertisementUrl;
    private String description;
    private int id;

    public String getAdvertisementLink() {
        return advertisementLink;
    }

    public void setAdvertisementLink(String advertisementLink) {
        this.advertisementLink = advertisementLink;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
