package com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.rickylagerkvist.sporifyapi.models.TrackList;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.example.rickylagerkvist.sporifyapi.SpotifyApi.APIUrlPaths;
import com.example.rickylagerkvist.sporifyapi.SpotifyApi.HttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by rickylagerkvist on 2017-02-21.
 */

public class SearchTrackViewModel extends BaseObservable {

    private List<TrackObject> mTracks;
    private Context context;
    private EditText mSearchEditText;
    private RelativeLayout mMainLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mNoTrackFoundLayout;
    private MvvmRecTrackAdapter mTrackResCardAdapter;

    public SearchTrackViewModel(List<TrackObject> tracks, Context context, EditText mSearchEditText,
                                RelativeLayout mMainLayout, final SwipeRefreshLayout mSwipeRefreshLayout,
                                LinearLayout mNoTrackFoundLayout, MvvmRecTrackAdapter mTrackResCardAdapter) {
        this.mTracks = tracks;
        this.context = context;
        this.mSearchEditText = mSearchEditText;
        this.mMainLayout = mMainLayout;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.mNoTrackFoundLayout = mNoTrackFoundLayout;
        this.mTrackResCardAdapter = mTrackResCardAdapter;

        // refresh layout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchTracks();
            }
        });

        // IME_ACTION_DONE
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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


    public View.OnClickListener onSearchTrack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTracks();
            }
        };
    }

    // search for mTracks
    public void searchTracks()  {

        String searchString = mSearchEditText.getText().toString();

        if(!searchString.isEmpty()){
            try {
                searchTrack(searchString);
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else {

            Snackbar snackbar = Snackbar.make(mMainLayout, "Search is empty!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        // Load complete
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void searchTrack(String searchText) throws JSONException {

        String newSearchText = searchText.replaceAll(" ", "+");

        HttpUtils.get(APIUrlPaths.searchTrack.replace("{search}", newSearchText), null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("asd", "---------------- this is response : " + response);

                TrackList tacksList = JSON.parseObject(response.toString(), TrackList.class);

                mTracks = tacksList.tracks.items;
                mTrackResCardAdapter.update(mTracks);

                // if list is empty show mNoTrackFoundLayout
                if(mTracks.isEmpty()){
                    mNoTrackFoundLayout.setVisibility(View.VISIBLE);
                } else {
                    mNoTrackFoundLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

            }
        });

        hideSoftInput();
    }

    public void hideSoftInput(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);

    }
}
