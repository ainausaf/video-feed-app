package com.example.myapplication.TutorialFeed;

import com.example.myapplication.dagger.AppComponent;
import com.example.myapplication.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
