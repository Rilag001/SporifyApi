package com.example.rickylagerkvist.sporifyapi.mvvmTest.trackdetails;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.models.Track;

/**
 * Created by rickylagerkvist on 2017-02-20.
 */

public class DetailTrackViewModel extends BaseObservable {

    private Track track;

    public DetailTrackViewModel(@NonNull final Track track) {
        this.track = track;
    }

    @Bindable
    public String getTitle(){
        return track.getName();
    }

    public String getImageUrl() {
        return track.getAlbum().getImages().get(0).getUrl();
    }

    @BindingAdapter({"image"})
    public static void loadCoverArt(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }
}
