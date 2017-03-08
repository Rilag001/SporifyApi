package com.example.rickylagerkvist.sporifyapi.mvvmTest.trackdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.databinding.FragmentTrackDetailBinding;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks.SearchTrackListFragment;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackDetailFragment extends Fragment {

    Track track;

    public TrackDetailFragment() {
        // Required empty public constructor
    }

    public static TrackDetailFragment newInstance()
    {
        return new TrackDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @NonNull final Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            String trackString = bundle.getString("track");
            Gson gson = new Gson();
            track = gson.fromJson(trackString, Track.class);
        }

        final View layout = inflater.inflate(R.layout.fragment_track_detail, container, false);
        FragmentTrackDetailBinding.bind(layout)
                .setViewModel(new DetailTrackViewModel(track));
        return layout;
    }

}
