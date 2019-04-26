package com.example.myapplication.TutorialFeedDetails;

import com.example.myapplication.data.Repository;
import com.example.myapplication.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TutorialFeedDetailsModule {

    TutorialFeedDetailsActivity detailsActivity;

    public TutorialFeedDetailsModule(TutorialFeedDetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
    }

    @Provides
    @ActivityScope
    public TutorialFeedDetailsContract.View provideDetailActivityView() {
        return this.detailsActivity;
    }

    @Provides
    @ActivityScope
    public TutorialFeedDetailsContract.Presenter providePresenter(TutorialFeedDetailsContract.View view, Repository repository) {
        return new TutorialFeedDetailsPresenter(view, repository);
    }
}
