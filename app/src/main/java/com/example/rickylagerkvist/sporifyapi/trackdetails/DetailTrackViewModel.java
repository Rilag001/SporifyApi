package com.example.rickylagerkvist.sporifyapi.trackdetails;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;

/**
 * Created by rickylagerkvist on 2017-02-20.
 */

public class DetailTrackViewModel extends BaseObservable {

    private TrackObject track;
    private Context context;

    public DetailTrackViewModel(TrackObject track, Context context) {
        this.track = track;
        this.context = context;
    }

    @Bindable
    public String getTitle(){
        return track.name;
    }

    public String getImageUrl() {
        return track.album.images.get(0).getUrl();
    }

    @BindingAdapter({"image"})
    public static void loadCoverArt(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);

    }
}
