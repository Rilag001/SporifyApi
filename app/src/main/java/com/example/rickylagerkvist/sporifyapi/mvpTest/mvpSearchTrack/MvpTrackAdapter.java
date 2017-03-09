package com.example.rickylagerkvist.sporifyapi.mvpTest.mvpSearchTrack;

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

class MvpTrackAdapter extends RecyclerView.Adapter<MvpTrackAdapter.MyViewHolder> {

    private List<Track> mTracks;

    MvpTrackAdapter(List<Track> trackObjects) {
        this.mTracks = trackObjects;
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

        final Track track = mTracks.get(position);

        // TrackText
        holder.trackText.setText(track.getName());

        // ArtistText
        holder.artistText.setText(track.getAllArtistAsJoinedString());

        // AlbumText
        holder.albumText.setText(track.getAlbum().getName());

        // CoverArt
        Glide.with(holder.coverArt.getContext()).load(track.getSmallCoverArt()).into(holder.coverArt);

        // open MvpTrackDetailFragment
        holder.trackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put model as String extra
                Gson gson = new Gson();
                String trackJson = gson.toJson(track);

                Bundle bundle = new Bundle();
                bundle.putString("track", trackJson); // Put anything what you want

                MvpTrackDetailFragment fragment = MvpTrackDetailFragment.newInstance();
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)v.getContext()).getSupportFragmentManager();
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
        return mTracks.size();
    }

    void update(List<Track> modelList){
        mTracks.clear();
        mTracks.addAll(modelList);
        notifyDataSetChanged();
    }
}
