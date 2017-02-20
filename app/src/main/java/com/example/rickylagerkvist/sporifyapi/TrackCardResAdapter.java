package com.example.rickylagerkvist.sporifyapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rickylagerkvist on 2017-02-19.
 */

public class TrackCardResAdapter extends RecyclerView.Adapter<TrackCardResAdapter.BindingHolder> {


    private List<TrackObject> list;
    private Context context;

    public TrackCardResAdapter(List<TrackObject> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class BindingHolder extends RecyclerView.ViewHolder{

        public RelativeLayout trackLayout;
        public ImageView coverArt;
        public TextView trackText, albumText;

        public BindingHolder(View itemView) {
            super(itemView);

            trackLayout = (RelativeLayout) itemView.findViewById(R.id.track_layout);
            coverArt = (ImageView) itemView.findViewById(R.id.track_card_imageView);
            trackText = (TextView) itemView.findViewById(R.id.trackTextView);
            albumText = (TextView) itemView.findViewById(R.id.albumTextView);
        }
    }

    @Override
    public TrackCardResAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.track_card, parent, false);
        return new BindingHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackCardResAdapter.BindingHolder holder, int position) {

        final TrackObject trackObject = list.get(position);

        Glide.with(context).load(trackObject.album.images.get(0).url).into(holder.coverArt);
        holder.trackText.setText(trackObject.name);
        holder.albumText.setText(trackObject.album.name);

        holder.trackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("url", trackObject.album.images.get(0).url); // Put anything what you want

                TrackDetailFragment fragment2 = new TrackDetailFragment();
                fragment2.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.activity_main, fragment2)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<TrackObject> modelList){
        list.clear();
        for (TrackObject model: modelList) {
            list.add(model);
        }
        notifyDataSetChanged();
    }

}
