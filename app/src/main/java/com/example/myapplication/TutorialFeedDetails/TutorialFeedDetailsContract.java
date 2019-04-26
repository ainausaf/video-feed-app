package com.example.myapplication.TutorialFeedDetails;

import com.example.myapplication.model.TutorialDetail;

public interface TutorialFeedDetailsContract {

    public interface View {

        void showErrorMessage();

    }

    public interface Presenter {

        void onCreate(TutorialDetail tutorialDetail);

    }
}
