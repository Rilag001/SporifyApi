package com.example.rickylagerkvist.sporifyapi;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.rickylagerkvist.sporifyapi.models.TrackList;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.example.rickylagerkvist.sporifyapi.utils.APIUrlPaths;
import com.example.rickylagerkvist.sporifyapi.utils.HttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTrackListFragment extends Fragment {

    // views
    EditText mSearchEditText;
    Button mSearchButton;
    RelativeLayout mMainLayout;
    RecyclerView mRecyclerView;
    LinearLayout mNoTrackFoundLayout;

    // list
    ArrayList<TrackObject> mTrackObjects = new ArrayList<>();
    TrackCardResAdapter mTrackResCardAdapter;


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

        // RecyclerAdapter
        mTrackResCardAdapter = new TrackCardResAdapter(mTrackObjects, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mTrackResCardAdapter);

        // search track
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTracks();
            }
        });

        // search track
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchTracks();

                    return true;
                }
                return false;
            }
        });

        return rootView;
    }


    public void searchTracks()  {

        String searchString = mSearchEditText.getText().toString();

        if(!searchString.isEmpty()){
            try {
                searchTrack(searchString);
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else {

            Snackbar snackbar = Snackbar.make(mMainLayout, "Search is empty!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    public void searchTrack(String searchText) throws JSONException {

        String newSearchText = searchText.replaceAll(" ", "+");

        HttpUtils.get(APIUrlPaths.searchTrack.replace("{search}", newSearchText), null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);

                TrackList tacksList = JSON.parseObject(response.toString(), TrackList.class);

                mTrackObjects = tacksList.tracks.items;
                mTrackResCardAdapter.update(mTrackObjects);

                // if list is empty show mNoTrackFoundLayout
                if(mTrackObjects.isEmpty()){
                    mNoTrackFoundLayout.setVisibility(View.VISIBLE);
                } else {
                    mNoTrackFoundLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });

        // hide soft keyboard
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

}
