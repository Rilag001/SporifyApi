package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.mvpTest.mvpTrackDetail.MvpTrackDetailFragment;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rickylagerkvist on 2017-02-24.
 */

public class MvpTrackAdapter extends RecyclerView.Adapter<MvpTrackAdapter.MyViewHolder> {

    private List<Track> trackObjects;
    private Context mContext;

    MvpTrackAdapter(List<Track> trackObjects, Context mContext) {
        this.trackObjects = trackObjects;
        this.mContext = mContext;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.mvp_track_card_imageView) ImageView coverArt;
        @BindView(R.id.mvp_trackTextView) TextView trackText;
        @BindView(R.id.mvp_albumTextView) TextView albumText;
        @BindView(R.id.mvp_artistTextView) TextView artistText;
        @BindView(R.id.mvp_track_layout) ConstraintLayout trackLayout;


        MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MvpTrackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mvp_track_card, parent, false);
        return new MyViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(MvpTrackAdapter.MyViewHolder holder, int position) {

        final Track trackObject = trackObjects.get(position);

        // TrackText
        holder.trackText.setText(trackObject.getName());

        // ArtistText
        holder.artistText.setText(trackObject.getAllArtistAsJoinedString());

        // AlbumText
        holder.albumText.setText(trackObject.getAlbum().getName());

        // CoverArt
        Glide.with(mContext).load(trackObject.getSmallCoverArt()).into(holder.coverArt);

        // open MvpTrackDetailFragment
        holder.trackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put model as String extra
                Gson gson = new Gson();
                String trackObjectJson = gson.toJson(trackObject);

                Bundle bundle = new Bundle();
                bundle.putString("track", trackObjectJson); // Put anything what you want

                MvpTrackDetailFragment fragment = MvpTrackDetailFragment.newInstance();
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.activity_mvp_main, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackObjects.size();
    }

    public void update(List<Track> modelList){
        trackObjects.clear();
        for (Track model: modelList) {
            trackObjects.add(model);
        }
        notifyDataSetChanged();
    }
}
