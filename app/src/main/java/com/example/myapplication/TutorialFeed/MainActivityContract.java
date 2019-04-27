package com.example.myapplication.TutorialFeed;

import com.example.myapplication.model.FeedData;
import com.example.myapplication.model.TutorialDetail;

import java.util.List;

public interface MainActivityContract {

    public interface View {

        void getVideoFeedData(FeedData feedData);

        void showProgressBar(boolean isDisplayProgressBar);

        void showErrorMessage();

        void navigateToTutorialDetails(List<TutorialDetail> tutorialDetail);
    }

    public interface Presenter {

       void onCreate();

        void getTutorialsData(int videoId);

        void getFeedData();
    }
}
