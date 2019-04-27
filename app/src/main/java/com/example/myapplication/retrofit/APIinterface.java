package com.example.myapplication.retrofit;

import com.example.myapplication.model.FeedData;
import com.example.myapplication.model.TutorialDetail;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface APIinterface {

    @GET("/youtube/home_feed")
    Observable<FeedData> getFeedData();

    //passing the Id parameter in the request
    @GET("/youtube/course_detail")
    Observable<List<TutorialDetail>> getTutorialDetails(@Query("id") int id);

}
