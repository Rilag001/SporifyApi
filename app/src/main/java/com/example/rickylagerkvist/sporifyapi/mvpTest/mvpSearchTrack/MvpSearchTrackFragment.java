package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpSearchTrackFragment extends Fragment implements SearchTrackPresenter.View{

    private SearchTrackPresenter searchTrackPresenter;
    @BindView(R.id.mvpTrackRecyclerView) RecyclerView recyclerView;
    private MvpTrackAdapter mvpTrackAdapter;
    private List<TrackObject> trackObjects = new ArrayList<>();

    @BindView(R.id.mvpSearchEditText) EditText searchEditText;
    @BindView(R.id.mvpSearchButton) Button searchButton;
    @BindView(R.id.mvp_no_internet_layout) LinearLayout noTrackFoundLayout;
    @BindView(R.id.mvpSwipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

    public MvpSearchTrackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mvp_search_track, container, false);

        ButterKnife.bind(this, rootView);

        // set adapter
        LinearLayoutManager linearLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutmanager);
        mvpTrackAdapter = new MvpTrackAdapter(trackObjects, getContext());
        recyclerView.setAdapter(mvpTrackAdapter);

        // set presenter
        searchTrackPresenter = new SearchTrackPresenter(this);

        // search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForTracks();
            }
        });

        // search on editorAction
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    searchForTracks();

                    return true;
                }
                return false;
            }
        });

        // SwipeRefresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchForTracks();
            }
        });

        return rootView;
    }

    private void searchForTracks() {
        if(!searchEditText.getText().toString().isEmpty()){
            try {
                searchTrackPresenter.searchTrack(searchEditText.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        swipeRefreshLayout.setRefreshing(false);

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }

    public void UpdateList(List<TrackObject> list){
        mvpTrackAdapter.update(list);
    }

    public void toggleNoTrackFoundLayout(boolean isEmpty){
        if(isEmpty){
            noTrackFoundLayout.setVisibility(View.VISIBLE);
        } else  {
            noTrackFoundLayout.setVisibility(View.GONE);
        }
    }

}
