package com.example.rickylagerkvist.sporifyapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rickylagerkvist.sporifyapi.databinding.FragmentTrackDetailBinding;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackDetailFragment extends Fragment {

//    ImageView coverArt;
//    TextView trackName;

    TrackObject track;

    public TrackDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView =  inflater.inflate(R.layout.fragment_track_detail, container, false);
//
//        coverArt = (ImageView) rootView.findViewById(R.id.imageUrl);
//        trackName = (TextView) rootView.findViewById(R.id.track_name_text);
//
//        Bundle bundle = this.getArguments();
//
//        if(bundle != null){
//            String trackString = bundle.getString("track");
//            Gson gson = new Gson();
//            TrackObject track = gson.fromJson(trackString, TrackObject.class);
//
//            Glide.with(getActivity()).load(track.album.images.get(0).url).into(coverArt);
//            trackName.setText(track.name);
//        }
//
//        return rootView;

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            String trackString = bundle.getString("track");
            Gson gson = new Gson();
            track = gson.fromJson(trackString, TrackObject.class);
        }

        final View layout = inflater.inflate(R.layout.fragment_track_detail, container, false);
        FragmentTrackDetailBinding.bind(layout)
                .setViewModel(new DetailTrackViewModel(track, getContext()));
        return layout;
    }

}
