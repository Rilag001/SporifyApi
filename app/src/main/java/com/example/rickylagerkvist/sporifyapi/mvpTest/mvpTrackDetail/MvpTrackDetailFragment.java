package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack.MvpSearchTrackFragment;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpTrackDetailFragment extends Fragment implements TrackDetailPresenter.View {

    Track track;
    TrackDetailPresenter presenter;
    @BindView(R.id.mvp_imageUrl) ImageView coverArtImageView;
    @BindView(R.id.mvp_track_name_text) TextView trackNameTextView;

    public MvpTrackDetailFragment() {
        // Required empty public constructor
    }

    public static MvpTrackDetailFragment newInstance()
    {
        return new MvpTrackDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mvp_track_detail, container, false);

        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            String trackString = bundle.getString("track");
            Gson gson = new Gson();
            track = gson.fromJson(trackString, Track.class);
        }

        presenter = new TrackDetailPresenter(this, track);

        return rootView;
    }

    @Override
    public void setCoverArt(String url) {
        Glide.with(getContext()).load(url).into(coverArtImageView);
    }

    @Override
    public void setTrackName(String name) {
        trackNameTextView.setText(name);
    }
}
