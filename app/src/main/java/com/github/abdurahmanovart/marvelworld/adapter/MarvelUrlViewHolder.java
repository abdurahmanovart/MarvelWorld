package com.github.abdurahmanovart.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.abdurahmanovart.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MarvelUrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CharacterClickListener mClickListener;

    @BindView(R.id.url_button)
    Button mButton;

    public MarvelUrlViewHolder(View itemView, CharacterClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mClickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}
