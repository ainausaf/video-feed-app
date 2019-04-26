package com.example.myapplication.TutorialFeed;

import com.example.myapplication.data.Repository;
import com.example.myapplication.model.FeedData;
import com.example.myapplication.model.TutorialDetail;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private Repository repository;


    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;

    }

    @Override
    public void onCreate() {
        view.showProgressBar(true);

        getFeedData();
    }

    @Override
    public void getRestaurantData(int videoId) {
        view.showProgressBar(true);
        Observable<List<TutorialDetail>> tutorialDetail = repository.getTutorialDetail(videoId);
        tutorialDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoTutorialDetail -> {
                    view.showProgressBar(false);
                    view.navigateToTutorialDetails(videoTutorialDetail);
                }, throwable -> {
                    view.showProgressBar(false);
                    view.showErrorMessage();
                });
    }


    public void getFeedData() {
        final Observable<FeedData> feedVideoData = repository.getFeedData();
        feedVideoData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FeedData>() {
                    @Override
                    public void call(FeedData feedData) {
                        view.getVideoFeedData(feedData);
                        view.showProgressBar(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showErrorMessage();
                        view.showProgressBar(false);
                    }
                });
    }
}
