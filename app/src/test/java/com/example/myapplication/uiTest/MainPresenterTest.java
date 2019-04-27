package com.example.myapplication.uiTest;


import com.example.myapplication.TutorialFeed.MainActivityContract;
import com.example.myapplication.TutorialFeed.MainActivityPresenter;
import com.example.myapplication.data.Repository;
import com.example.myapplication.model.FeedData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Presenter
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainActivityContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private FeedData feedData;

    private MainActivityContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new MainActivityPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void getFeedData_testCallBack(){
        feedData = new FeedData();
        when(mockDataRepository.getFeedData()).thenReturn(Observable.just(feedData));

        presenter.getFeedData();

        verify(mockDataRepository).getFeedData();

        verify(mockView).getVideoFeedData(feedData);

        verify(mockView, times(1)).showProgressBar(false);
    }
}
