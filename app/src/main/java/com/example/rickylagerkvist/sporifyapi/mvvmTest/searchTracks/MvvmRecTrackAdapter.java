package com.example.rickylagerkvist.sporifyapi.mvvmTest.searchTracks;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.databinding.TrackConstCardBinding;
import com.example.rickylagerkvist.sporifyapi.models.Track;
import com.example.rickylagerkvist.sporifyapi.mvvmTest.trackdetails.TrackDetailFragment;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by rickylagerkvist on 2017-02-20.
 */

public class MvvmRecTrackAdapter extends RecyclerView.Adapter<MvvmRecTrackAdapter.BindingHolder> {

    private List<Track> mTracks;
    private Context mContext;

    public MvvmRecTrackAdapter(List<Track> trackObjects, Context context) {
        this.mTracks = trackObjects;
        this.mContext = context;
    }

    @Override
    public BindingHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        TrackConstCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.track_const_card, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final BindingHolder holder, int position) {

        final Track trackObject = mTracks.get(position);

        holder.mBinding.setListItem(trackObject);

        // TODO Use binding instead?
        holder.mBinding.trackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put model as String extra
                Gson gson = new Gson();
                String trackObjectJson = gson.toJson(trackObject);

                Bundle bundle = new Bundle();
                bundle.putString("track", trackObjectJson); // Put anything what you want

                TrackDetailFragment fragment = TrackDetailFragment.newInstance();
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.activity_main, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private final TrackConstCardBinding mBinding;

        public BindingHolder(TrackConstCardBinding binding) {
            super(binding.trackLayout);
            mBinding = binding;
        }
    }

    public void update(List<Track> modelList){
        mTracks.clear();
        mTracks.addAll(modelList);
        notifyDataSetChanged();
    }

    @BindingAdapter({"image"})
    public static void loadCoverArt(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }

}
