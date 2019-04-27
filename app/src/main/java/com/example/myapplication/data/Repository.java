package com.example.myapplication.data;

import com.example.myapplication.model.FeedData;
import com.example.myapplication.model.TutorialDetail;

import java.util.List;
import java.util.Map;

import rx.Observable;

public class Repository {

    private DataSource remoteDataSource;

    public Repository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    //to get the list of tutorial for the first screen
    public Observable<FeedData> getFeedData() {

        Observable<FeedData> observable;

        observable = remoteDataSource.getFeedDataForVideos();

        return observable;
    }

    //to get the tutorial details list
    public Observable<List<TutorialDetail>> getTutorialDetail(int videoId) {

        Observable<List<TutorialDetail>> observable;

        observable = remoteDataSource.getVideoTutorialDetail(videoId);

        return observable;
    }
}
