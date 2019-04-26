package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.dagger.AppComponent;
import com.example.myapplication.dagger.AppModule;
import com.example.myapplication.dagger.DaggerAppComponent;
import com.example.myapplication.retrofit.RetrofitModule;

import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.annotation.AcraCore;

/*
Main Application builds Dagger App component
 */
@AcraCore(buildConfigClass = BuildConfig.class)
public class TutorialFeedApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
