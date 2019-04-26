package com.example.myapplication.dagger;

import com.example.myapplication.TutorialFeedApplication;
import com.example.myapplication.retrofit.RetrofitModule;

import dagger.Module;

@Module(includes = RetrofitModule.class)
public class AppModule {
    private TutorialFeedApplication baseApplication;

    public AppModule(TutorialFeedApplication baseApplication) {
        this.baseApplication = baseApplication;
    }
}
