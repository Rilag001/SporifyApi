package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSON;
import com.example.rickylagerkvist.sporifyapi.SpotifyApi.APIUrlPaths;
import com.example.rickylagerkvist.sporifyapi.SpotifyApi.HttpUtils;
import com.example.rickylagerkvist.sporifyapi.models.TrackList;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by rickylagerkvist on 2017-02-24.
 */

public class SearchTrackPresenter {

    private View view;

    public SearchTrackPresenter(View view) {
        this.view = view;
    }

    public void searchTrack(String searchText) throws JSONException {

        String newSearchText = searchText.replaceAll(" ", "+");

        HttpUtils.get(APIUrlPaths.searchTrack.replace("{search}", newSearchText), null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);

                TrackList tacksList = JSON.parseObject(response.toString(), TrackList.class);

                List<TrackObject> tracks = tacksList.tracks.items;
                view.UpdateList(tracks);
                view.toggleNoTrackFoundLayout(tracks.isEmpty());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });
    }

    public interface View {
        void UpdateList(List<TrackObject> list);
        void toggleNoTrackFoundLayout(boolean isTracksEmpty);
    }



}
