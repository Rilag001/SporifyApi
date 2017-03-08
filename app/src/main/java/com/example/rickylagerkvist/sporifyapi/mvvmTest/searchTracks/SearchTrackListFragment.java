package com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.databinding.FragmentSearchTrackListBinding;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.mvvmTest.trackdetails.TrackDetailFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class SearchTrackListFragment extends Fragment implements SearchTrackViewModel.View {

    //region members
    EditText mSearchEditText;
    Button mSearchButton;
    FrameLayout mMainLayout;
    RecyclerView mRecyclerView;
    TextView mNoTrackFoundLayout;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<Track> mTrackObjects;
    MvvmRecTrackAdapter mTrackResCardAdapter;
    //endregion

    public SearchTrackListFragment() {
        // Required empty public constructor
    }

    public static SearchTrackListFragment newInstance()
    {
        return new SearchTrackListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @NonNull final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_track_list, container, false);

        mTrackObjects = new ArrayList<>();

        mSearchEditText = (EditText) rootView.findViewById(R.id.searchEditText);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_trackRecyclerView);
        mSearchButton = (Button) rootView.findViewById(R.id.searchButton);
        mMainLayout = (FrameLayout) rootView.findViewById(R.id.activity_main);
        mNoTrackFoundLayout = (TextView) rootView.findViewById(R.id.ll_no_internet_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        // RecyclerAdapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mTrackResCardAdapter = new MvvmRecTrackAdapter(mTrackObjects);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mTrackResCardAdapter);

        // set ViewModel
        FragmentSearchTrackListBinding.bind(rootView)
                .setViewModel(new SearchTrackViewModel(mTrackObjects, this));

        return rootView;
    }

    public String getSearchText(){
        return mSearchEditText.getText().toString();
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(){
        return mSwipeRefreshLayout;
    }

    public EditText getSearchEditText() {
        return mSearchEditText;
    }

    public void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showSnackBarSearchIsEmpty(){
        Snackbar snackbar = Snackbar.make(mMainLayout, "Search is empty!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void hideSoftInput() {

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }

    @Override
    public TextView getNoTrackFountLayout() {
        return mNoTrackFoundLayout;
    }

    @Override
    public void updateTracks(List<Track> modelList) {
        mTrackResCardAdapter.update(modelList);
    }



}
