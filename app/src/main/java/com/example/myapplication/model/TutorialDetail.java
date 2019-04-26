package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TutorialDetail implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("link")
    @Expose
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
