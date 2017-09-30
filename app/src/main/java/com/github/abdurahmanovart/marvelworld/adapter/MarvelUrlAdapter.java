package com.github.abdurahmanovart.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdurahmanovart.marvelworld.R;
import com.github.abdurahmanovart.marvelworld.bean.MarvelUrl;

import java.util.List;

/**
 * @author Likhtman Hanna on 03.09.2017.
 */

public class MarvelUrlAdapter extends RecyclerView.Adapter<MarvelUrlViewHolder> {

    private CharacterClickListener mClickListener;
    private List<MarvelUrl> mMarvelUrls;

    public MarvelUrlAdapter(CharacterClickListener clickListener,
                            List<MarvelUrl> marvelUrls) {
        mClickListener = clickListener;
        mMarvelUrls = marvelUrls;
    }

    @Override
    public MarvelUrlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_url_layout, null);
        return new MarvelUrlViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(MarvelUrlViewHolder marvelUrlViewHolder, int position) {
        MarvelUrl marvelUrl = mMarvelUrls.get(position);
        marvelUrlViewHolder.mButton.setText(marvelUrl.getType());
    }

    @Override
    public int getItemCount() {
        return mMarvelUrls.size();
    }
}
