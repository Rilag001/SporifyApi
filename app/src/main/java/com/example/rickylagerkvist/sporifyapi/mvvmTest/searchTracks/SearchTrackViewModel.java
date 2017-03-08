package com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.models.TrackList;
import com.example.rickylagerkvist.sporifyapi.ApiSpotify.HttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by rickylagerkvist on 2017-02-21.
 */

public class SearchTrackViewModel extends BaseObservable {

    private List<Track> mTracks;
    private View mView;

    SearchTrackViewModel(List<Track> tracks, View view) {
        this.mTracks = tracks;
        this.mView = view;

        // refresh layout
        view.getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchTracks();
            }
        });

        // IME_ACTION_DONE
        view.getSearchEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    searchTracks();

                    return true;
                }
                return false;
            }
        });
    }

    public android.view.View.OnClickListener onSearchTrack() {
        return new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                searchTracks();
            }
        };
    }

    // search for mTracks
    private void searchTracks()  {

        String searchString = mView.getSearchText();

        if(!searchString.isEmpty()){
            try {
                searchTrack(searchString);
            } catch (Exception e) {
                mView.showToast(e.toString());
            }
        } else {
            mView.showSnackBarSearchIsEmpty();
        }

        // Load complete
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        mView.getSwipeRefreshLayout().setRefreshing(false);
    }

    private void searchTrack(String searchText) throws JSONException {

        String newSearchText = searchText.replaceAll(" ", "+");

        HttpUtils.get(HttpUtils.searchTrack.replace("{search}", newSearchText), null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);

                TrackList tacksList = JSON.parseObject(response.toString(), TrackList.class);

                mTracks = tacksList.tracks.getItems();
                mView.updateTracks(mTracks);

                // if list is empty show mNoTrackFoundLayout
                if(mTracks.isEmpty()){
                    mView.getNoTrackFountLayout().setVisibility(android.view.View.VISIBLE);
                } else {
                    mView.getNoTrackFountLayout().setVisibility(android.view.View.GONE);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });

        mView.hideSoftInput();
    }

    public interface View {
        String getSearchText();
        SwipeRefreshLayout getSwipeRefreshLayout();
        EditText getSearchEditText();
        void showToast(String message);
        void showSnackBarSearchIsEmpty();
        void hideSoftInput();
        TextView getNoTrackFountLayout();
        void updateTracks(List<Track> modelList);
    }
}
