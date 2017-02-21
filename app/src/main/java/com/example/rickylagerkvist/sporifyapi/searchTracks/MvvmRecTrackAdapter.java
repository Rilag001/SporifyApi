package com.example.rickylagerkvist.sporifyapi.searchTracks;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rickylagerkvist.sporifyapi.R;
import com.example.rickylagerkvist.sporifyapi.databinding.TrackCardBinding;
import com.example.rickylagerkvist.sporifyapi.models.TrackObject;
import com.example.rickylagerkvist.sporifyapi.trackdetails.TrackDetailFragment;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by rickylagerkvist on 2017-02-20.
 */

public class MvvmRecTrackAdapter extends RecyclerView.Adapter<MvvmRecTrackAdapter.BindingHolder> {

    private List<TrackObject> trackObjects;
    private Context mContext;

    public MvvmRecTrackAdapter(List<TrackObject> trackObjects, Context mContext) {
        this.trackObjects = trackObjects;
        this.mContext = mContext;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.track_card, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        holder.mBinding.setListItem(trackObjects.get(position));

        holder.mBinding.trackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put model as String extra
                Gson gson = new Gson();
                String trackObjectJson = gson.toJson(trackObjects.get(position));

                Bundle bundle = new Bundle();
                bundle.putString("track", trackObjectJson); // Put anything what you want

                TrackDetailFragment fragment2 = new TrackDetailFragment();
                fragment2.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
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
        return trackObjects.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private final TrackCardBinding mBinding;

        public BindingHolder(TrackCardBinding binding) {
            super(binding.trackLayout);
            mBinding = binding;
        }
    }

    public void update(List<TrackObject> modelList){
        trackObjects.clear();
        for (TrackObject model: modelList) {
            trackObjects.add(model);
        }
        notifyDataSetChanged();
    }

    @BindingAdapter({"image"})
    public static void loadCoverArt(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }

}
