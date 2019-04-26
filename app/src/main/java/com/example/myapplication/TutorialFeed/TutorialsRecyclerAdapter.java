package com.example.myapplication.TutorialFeed;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.myapplication.R;
import com.example.myapplication.model.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorialsRecyclerAdapter extends RecyclerView.Adapter<TutorialsRecyclerAdapter .ViewHolder> {

    private Context context;
    private List<Video> videos;
    private final OnItemClickListener listener;


    public TutorialsRecyclerAdapter(Context context,
                                    List<Video> videos, OnItemClickListener listener) {
        this.context = context;
        this.videos = videos;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardview_feed)
        CardView cardViewFeed;
        @BindView(R.id.iv_image)
        ImageView feedImage;
        @BindView(R.id.tv_profile_image)
        ImageView ivProfileImage;
        @BindView(R.id.training_course_name)
        TextView courseName;
        @BindView(R.id.number_of_views)
        TextView viewNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public TutorialsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorialsRecyclerAdapter.ViewHolder viewHolder, int position) {

        Video video = videos.get(position);
        String feedImageUrl = video.getImageUrl();
        String profileImageURL = video.getChannel().getProfileImageUrl();
        Glide.with(context).load(feedImageUrl).placeholder(
                R.drawable.layout_placeholder).error(
                R.drawable.layout_placeholder).into(viewHolder.feedImage);

        Glide.with(context).load(profileImageURL).asBitmap().centerCrop().into(new BitmapImageViewTarget(viewHolder.ivProfileImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                viewHolder.ivProfileImage.setImageDrawable(circularBitmapDrawable);
            }
        });

        viewHolder.courseName.setText(video.getName());
        viewHolder.viewNumber.setText("Number of views:"+video.getNumberOfViews());

        viewHolder.cardViewFeed.setOnClickListener(v -> listener.onItemClick(videos.get(viewHolder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        if(videos!=null) {
            return videos.size();
        }
        return 0;
    }

  public void clear() {
        int size = getItemCount();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<Video> videos) {
        this.videos.addAll(videos);
        notifyDataSetChanged();

    }

    public interface OnItemClickListener {
        void onItemClick(Video video);
    }
}
