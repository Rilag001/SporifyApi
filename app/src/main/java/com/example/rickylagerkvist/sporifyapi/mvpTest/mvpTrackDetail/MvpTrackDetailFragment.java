package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpTrackDetailFragment extends Fragment implements TrackDetailPresenter.View {

    //region members
    Track mTrack;
    TrackDetailPresenter mPresenter;
    @BindView(R.id.mvp_imageUrl) ImageView mCoverArtImageView;
    @BindView(R.id.mvp_track_name_text) TextView mTrackNameTextView;
    @BindView(R.id.rl_mvp_detail_main_layout) RelativeLayout mMainLayout;
    //end region

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

        View rootView = inflater.inflate(R.layout.fragment_mvp_track_detail, container, false);

        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            String trackString = bundle.getString("track");
            Gson gson = new Gson();
            mTrack = gson.fromJson(trackString, Track.class);
            mPresenter = new TrackDetailPresenter(this, mTrack);
        } else {
            Snackbar snackbar = Snackbar.make(mMainLayout, getResources().getString(R.string.could_not_load_data), Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        return rootView;
    }

    @Override
    public void setCoverArt(String url) {
        Glide.with(getContext()).load(url).into(mCoverArtImageView);
    }

    @Override
    public void setTrackName(String name) {
        mTrackNameTextView.setText(name);
    }
}
