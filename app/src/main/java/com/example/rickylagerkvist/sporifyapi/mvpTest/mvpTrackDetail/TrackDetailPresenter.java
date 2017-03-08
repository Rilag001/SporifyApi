package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail;

import com.example.rickylagerkvist.sporifyapi.models.Track;

/**
 * Created by rickylagerkvist on 2017-02-24.
 */

public class TrackDetailPresenter {

    private View view;
    Track trackObject;

    public TrackDetailPresenter(View view, Track trackObject) {
        this.view = view;
        this.trackObject = trackObject;

        view.setCoverArt(trackObject.getAlbum().getImages().get(0).getUrl());
        view.setTrackName(trackObject.getName());
    }

    public interface View {
        void setCoverArt(String url);
        void setTrackName(String name);
    }


}
