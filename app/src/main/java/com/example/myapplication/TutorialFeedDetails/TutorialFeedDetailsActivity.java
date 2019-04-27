package com.example.myapplication.TutorialFeedDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.TutorialDetail;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TutorialFeedDetailsActivity extends BaseActivity implements TutorialFeedDetailsContract.View {

    @BindView(R.id.iv_tutorial_image)
    ImageView tutorialImage;
    @BindView(R.id.tutorial_duration)
    TextView tutorialDuration;
    @BindView(R.id.tutorial_name)
    TextView tutorialName;


    private TutorialFeedDetailsComponent tutorialFeedDetailsComponent;;
    private List<TutorialDetail> tutorialList;
    private TutorialDetail tutorialDetail;

    @Inject
    TutorialFeedDetailsContract.Presenter detailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        initTutorialDetailDaggerComponent();
        setContentView(R.layout.activity_tutorial_details);
        ButterKnife.bind(this);
        tutorialList = (List<TutorialDetail>) getIntent().getSerializableExtra("tutorialDetails");
        initViews();
        setClickOnListeners();
    }
    /*
    Just taking the first item from the list and showing the details of that item.
    * */

    //I could have shown the whole list in the recycler view again, but after checking the data,
    // the item in the list were duplicate, so just retrieving the first item
    private void initViews() {
        tutorialDetail = tutorialList.get(0);
        tutorialName.setText(tutorialDetail.getName());
        tutorialDuration.setText("Video Duration:"+tutorialDetail.getDuration());
        String tutorialLink = tutorialDetail.getImageUrl();
        Glide.with(this).load(tutorialLink).placeholder(
                R.drawable.layout_placeholder).error(
                R.drawable.layout_placeholder).into(tutorialImage);
    }
    /*
    On click of the item, it opens the link in the brower
    */
    private void setClickOnListeners() {
        tutorialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tutorialDetail.getLink()));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this,"Error while loading the tutorial detail informaition",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void initTutorialDetailDaggerComponent() {
        super.initDaggerComponent();
        if(tutorialFeedDetailsComponent == null){
            tutorialFeedDetailsComponent = DaggerTutorialFeedDetailsComponent.builder()
                    .appComponent(appComponent)
                    .tutorialFeedDetailsModule(new TutorialFeedDetailsModule(this))
                    .build();

            tutorialFeedDetailsComponent.inject(this);
        }

    }
}
