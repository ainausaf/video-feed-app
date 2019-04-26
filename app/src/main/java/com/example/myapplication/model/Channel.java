package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profileImageUrl")
    @Expose
    private String profileImageUrl;
    @SerializedName("numberOfSubscribers")
    @Expose
    private Integer numberOfSubscribers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getNumberOfSubscribers() {
        return numberOfSubscribers;
    }

    public void setNumberOfSubscribers(Integer numberOfSubscribers) {
        this.numberOfSubscribers = numberOfSubscribers;
    }

}
