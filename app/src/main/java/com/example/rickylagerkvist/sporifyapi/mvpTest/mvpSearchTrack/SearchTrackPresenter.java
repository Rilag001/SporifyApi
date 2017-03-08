package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.rickylagerkvist.sporifyapi.ApiSpotify.HttpUtils;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.models.TrackList;
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

        String newSearchText = HttpUtils.searchTrack.replace("{search}", searchText.replace(" ", "+"));

        HttpUtils.get(newSearchText, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);

                TrackList tacksList = JSON.parseObject(response.toString(), TrackList.class);

                List<Track> tracks = tacksList.getTracks().getItems();
                view.UpdateList(tracks);
                view.toggleNoTrackFoundLayout(tracks.isEmpty());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });
    }

    public interface View {
        void UpdateList(List<Track> list);
        void toggleNoTrackFoundLayout(boolean isTracksEmpty);
    }



}
