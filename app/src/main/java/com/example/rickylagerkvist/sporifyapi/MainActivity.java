package com.example.rickylagerkvist.sporifyapi;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchTrackListFragment alarmSettingFragment = new SearchTrackListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_main, alarmSettingFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
