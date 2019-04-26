package com.example.myapplication.TutorialFeedDetails;

import com.example.myapplication.data.Repository;
import com.example.myapplication.model.TutorialDetail;

public class TutorialFeedDetailsPresenter implements TutorialFeedDetailsContract.Presenter {

    private Repository repository;


    private TutorialFeedDetailsContract.View view;

    public TutorialFeedDetailsPresenter(TutorialFeedDetailsContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;

    }
    @Override
    public void onCreate(TutorialDetail tutorialDetail) {
        if(tutorialDetail == null) {
            view.showErrorMessage();
            return;
        }
    }
}
