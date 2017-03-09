package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.models.Track;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpSearchTrackFragment extends Fragment implements SearchTrackPresenter.View {

    // region members
    private SearchTrackPresenter mSearchTrackPresenter;
    private MvpTrackAdapter mMvpTrackAdapter;
    private List<Track> mTrackObjects = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutmanager;
    private RelativeLayout mMainLayout;
    @BindView(R.id.mvpTrackRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.mvpSearchEditText) EditText mSearchEditText;
    @BindView(R.id.mvpSearchButton) Button mSearchButton;
    @BindView(R.id.mvp_no_internet_layout) TextView mNoTrackFoundTextView;
    @BindView(R.id.mvpSwipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    // end region

    public MvpSearchTrackFragment() {
        // Required empty public constructor
    }

    public static MvpSearchTrackFragment newInstance()
    {
        return new MvpSearchTrackFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mvp_search_track, container, false);

        ButterKnife.bind(this, rootView);
        mMainLayout = (RelativeLayout) rootView.findViewById(R.id.rl_main_layout);

        // set adapter
        mLinearLayoutmanager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutmanager);
        mMvpTrackAdapter = new MvpTrackAdapter(mTrackObjects);
        mRecyclerView.setAdapter(mMvpTrackAdapter);

        // set presenter
        mSearchTrackPresenter = new SearchTrackPresenter(this);

        // search button
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchForTracks();
            }
        });

        // search on editorAction
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    SearchForTracks();

                    return true;
                }
                return false;
            }
        });

        // SwipeRefresh
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SearchForTracks();
            }
        });

        return rootView;
    }

    private void SearchForTracks() {

        try {
            mSearchTrackPresenter.searchTrack(mSearchEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mSwipeRefreshLayout.setRefreshing(false);

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }

    public void updateList(List<Track> list){
        mMvpTrackAdapter.update(list);
    }

    public void toggleNoTrackFoundLayout(boolean isEmpty){
        if(isEmpty){
            mNoTrackFoundTextView.setVisibility(View.VISIBLE);
        } else  {
            mNoTrackFoundTextView.setVisibility(View.GONE);
        }
    }

    public String searchEditText(){
        return mSearchEditText.getText().toString();
    }

    public void showSnackBarSearchIsEmpty(){
        Snackbar snackbar = Snackbar.make(mMainLayout, "Search is empty!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


}
