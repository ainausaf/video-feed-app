package com.example.myapplication.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedData {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
