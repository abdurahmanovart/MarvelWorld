package com.github.abdurahmanovart.marvelworld;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.abdurahmanovart.marvelworld.adapter.CharacterClickListener;
import com.github.abdurahmanovart.marvelworld.adapter.MarvelUrlAdapter;
import com.github.abdurahmanovart.marvelworld.bean.MarvelUrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarvelUrlFragment extends Fragment implements CharacterClickListener{

    private static final String EXTRA_MARVEL_URLS = "extra_marvel_urls";


    private List<MarvelUrl> mMarvelUrls;

    private OnItemClickListener mListener;
    private MarvelUrlAdapter mMarvelUrlAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static MarvelUrlFragment newInstance(ArrayList<MarvelUrl> marvelUrls) {
        MarvelUrlFragment fragment = new MarvelUrlFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_MARVEL_URLS, marvelUrls);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMarvelUrls = getArguments().getParcelableArrayList(EXTRA_MARVEL_URLS);
            mMarvelUrlAdapter = new MarvelUrlAdapter(this, mMarvelUrls);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mMarvelUrlAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        if (mListener != null) {
            mListener.onMarvelUrlClick(mMarvelUrls.get(position));
        }
    }

    public interface OnItemClickListener {
        void onMarvelUrlClick(MarvelUrl character);
    }
}
