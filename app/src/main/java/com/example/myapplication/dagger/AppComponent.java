package com.example.myapplication.dagger;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.TutorialFeed.MainActivityModule;
import com.example.myapplication.data.Repository;
import com.example.myapplication.retrofit.RetrofitModule;
import com.example.myapplication.scope.ApplicationScope;

import dagger.Component;

/**
 * Dagger App Component with Application Scope
 */

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class, MainActivityModule.class})
public interface AppComponent {
    Repository getDataRepository();

    void inject(BaseActivity baseActivity);
}
