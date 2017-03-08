package com.example.rickylagerkvist.sporifyapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.rickylagerkvist.sporifyapi.mvpTest.MvpMainActivity;
import com.example.rickylagerkvist.sporifyapi.mvvmTest.MvvmMainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.startImage)
    ImageView startImage;

    @Override
    protected void onCreate(@NonNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

        Button startMvvmActivityButton = (Button) findViewById(R.id.test_mvvm_button);
        Button startMvpActivityButton = (Button) findViewById(R.id.test_mvp_button);

        startMvvmActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MvvmMainActivity.class);
                startActivity(intent);
            }
        });

        startMvpActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MvpMainActivity.class);
                startActivity(intent);
            }
        });

        Glide
            .with(this)
            .load("https://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png?itok=xhm7jaxS")
            .crossFade() //?
            .into(startImage);
    }
}
