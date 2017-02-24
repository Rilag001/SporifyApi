package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail;

import android.view.View;

import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack.SearchTrackPresenter;

/**
 * Created by rickylagerkvist on 2017-02-24.
 */

public class TrackDetailPresenter {

    private View view;
    TrackObject trackObject;

    public TrackDetailPresenter(View view, TrackObject trackObject) {
        this.view = view;
        this.trackObject = trackObject;

        view.setCoverArt(trackObject.album.images.get(0).url);
        view.setTrackName(trackObject.name);
    }

    public interface View {
        void setCoverArt(String url);
        void setTrackName(String name);
    }


}
