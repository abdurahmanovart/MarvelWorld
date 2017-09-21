package com.github.abdurahmanovart.marvelworld;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdurahmanovart.marvelworld.bean.MarvelCharacter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CharacterFragment extends Fragment {

    private static final String EXTRA_CHARACTERS = "extra_characters";
    public static final String TAG = CharacterFragment.class.getSimpleName();

    private ArrayList<MarvelCharacter> mCharacters;

    private OnItemClickListener mListener;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public static CharacterFragment newInstance(ArrayList<MarvelCharacter> characters) {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_CHARACTERS, characters);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacters = getArguments().getParcelableArrayList(EXTRA_CHARACTERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(container);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onButtonPressed(MarvelCharacter character) {
        if (mListener != null) {
            mListener.onFragmentInteraction(character);
        }
    }

    public interface OnItemClickListener {
        void onFragmentInteraction(MarvelCharacter character);
    }
}
