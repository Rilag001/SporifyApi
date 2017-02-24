package com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.databinding.FragmentSearchTrackListBinding;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchTrackListFragment extends Fragment {

    // views
    EditText mSearchEditText;
    Button mSearchButton;
    RelativeLayout mMainLayout;
    RecyclerView mRecyclerView;
    LinearLayout mNoTrackFoundLayout;
    SwipeRefreshLayout mSwipeRefreshLayout;

    // list
    ArrayList<TrackObject> mTrackObjects = new ArrayList<>();
    MvvmRecTrackAdapter mTrackResCardAdapter;


    public SearchTrackListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_track_list, container, false);


        // views
        mSearchEditText = (EditText) rootView.findViewById(R.id.searchEditText);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.trackRecyclerView);
        mSearchButton = (Button) rootView.findViewById(R.id.searchButton);
        mMainLayout = (RelativeLayout) rootView.findViewById(R.id.activity_main);
        mNoTrackFoundLayout = (LinearLayout) rootView.findViewById(R.id.no_internet_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        // RecyclerAdapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mTrackResCardAdapter = new MvvmRecTrackAdapter(mTrackObjects, getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mTrackResCardAdapter);

        // set ViewModel
        FragmentSearchTrackListBinding.bind(rootView)
                .setViewModel(new SearchTrackViewModel(mTrackObjects, getContext(), mSearchEditText, mMainLayout,
                        mSwipeRefreshLayout, mNoTrackFoundLayout, mTrackResCardAdapter));

        return rootView;
    }
}
