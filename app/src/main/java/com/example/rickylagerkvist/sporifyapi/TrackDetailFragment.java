package com.example.rickylagerkvist.sporifyapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackDetailFragment extends Fragment {

    ImageView coverArt;

    public TrackDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_track_detail, container, false);

        coverArt = (ImageView) rootView.findViewById(R.id.imageUrl);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            String coverUrl = bundle.getString("url");
            Glide.with(getActivity()).load(coverUrl).into(coverArt);
        }

        return rootView;
    }

}
