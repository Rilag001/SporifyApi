package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail;

import com.example.rickylagerkvist.sporifyapi.models.Track;

/**
 * Created by rickylagerkvist on 2017-02-24.
 */

public class TrackDetailPresenter {

    private View mView;
    private Track mTrack;

    TrackDetailPresenter(View view, Track track) {
        this.mView = view;
        this.mTrack = track;

        view.setCoverArt(track.getAlbum().getImages().get(0).getUrl());
        view.setTrackName(track.getName());
    }

    public interface View {
        void setCoverArt(String url);
        void setTrackName(String name);
    }


}
