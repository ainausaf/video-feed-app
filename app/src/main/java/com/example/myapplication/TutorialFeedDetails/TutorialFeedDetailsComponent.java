package com.example.myapplication.TutorialFeedDetails;

import com.example.myapplication.dagger.AppComponent;
import com.example.myapplication.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {TutorialFeedDetailsModule.class})
public interface TutorialFeedDetailsComponent {

    default void inject(TutorialFeedDetailsActivity detailsActivity) {

    }
}
