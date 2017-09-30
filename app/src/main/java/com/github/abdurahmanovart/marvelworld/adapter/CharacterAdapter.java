package com.github.abdurahmanovart.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdurahmanovart.marvelworld.R;
import com.github.abdurahmanovart.marvelworld.bean.MarvelCharacter;
import com.github.abdurahmanovart.marvelworld.bean.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Likhtman Hanna on 19.08.2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<MarvelCharacter> mCharacterList;
    private CharacterClickListener mClickListener;

    public CharacterAdapter(List<MarvelCharacter> characterList,
                            CharacterClickListener clickListener) {
        mCharacterList = characterList;
        mClickListener = clickListener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_layout, null);
        return new CharacterViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        MarvelCharacter character = mCharacterList.get(position);
        holder.mNameTextView.setText(character.getName());
        holder.mDescription.setText(character.getDescription());
        Picasso.with(holder.itemView.getContext())
                .load(character.getThumbnail().getFullPath(Thumbnail.PORTRAIT_FANTASTIC))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }
}
