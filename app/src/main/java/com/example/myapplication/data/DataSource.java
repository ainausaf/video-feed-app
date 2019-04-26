package com.example.myapplication.data;

import com.example.myapplication.model.FeedData;
import com.example.myapplication.model.TutorialDetail;
import com.example.myapplication.model.Video;
import com.example.myapplication.retrofit.APIinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class DataSource {

    private APIinterface apiInterface;

    // Memory cache of data
    private FeedData memory = null;

    private Map<String, String> queryMap;

    public DataSource(APIinterface apiInterface) {
        this.apiInterface = apiInterface;

    }

    Observable<FeedData> getFeedDataForVideos() {

        return Observable.concat(
                memory(),
                network()
        ).first(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object data) {
                return data != null;
            }
        });
    }

    Observable<List<TutorialDetail>> getVideoTutorialDetail(int videoId) {

        return apiInterface.getTutorialDetails(videoId)
                .flatMap(Observable::just);
    }

    public Observable<FeedData> network() {
        return apiInterface.getFeedData()
                .flatMap(new Func1<FeedData, Observable<? extends FeedData>>() {
                    @Override
                    public Observable<? extends FeedData> call(FeedData feedData) {
                        return DataSource.this.processFeedDataResponse(feedData);
                    }
                });
    }

    private Observable<FeedData> processFeedDataResponse(FeedData feedData) {
        List<Video> videosList = new ArrayList<>();
        videosList.addAll(feedData.getVideos());
        memory = feedData;
        return Observable.just(feedData).doOnNext(new Action1<FeedData>() {
            @Override
            public void call(FeedData data) {
                memory = data;
            }
        });
    }

    public Observable<FeedData>  memory() {
        Observable<FeedData> observable = Observable.create(new Observable.OnSubscribe<FeedData>() {
            @Override
            public void call(Subscriber<? super FeedData> subscriber) {
                subscriber.onNext(memory);
                subscriber.onCompleted();
            }
        });
        return observable;
    }
}
