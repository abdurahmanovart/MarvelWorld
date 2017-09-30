package com.github.abdurahmanovart.marvelworld;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.abdurahmanovart.marvelworld.bean.BaseResponse;
import com.github.abdurahmanovart.marvelworld.bean.MarvelCharacter;
import com.github.abdurahmanovart.marvelworld.net.ApiClient;
import com.github.abdurahmanovart.marvelworld.net.MarvelService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CharacterFragment.OnItemClickListener {

    public static final String API_KEY = "181a9f0e6d0b25babcb3cdf1c7075dd5";
    public static final String HASH = "cc07634e8e625376a3054614629a51ef";
    public static final String TAG = MainActivity.class.getSimpleName();

    private MarvelService mService;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initToolbar();
    }

    @Override
    public void onFragmentInteraction(MarvelCharacter character) {
        startActivity(CharacterActivity.createExplicitIntent(this, character));
    }

    //region private method

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Utils.hasConnection(getApplicationContext())) {
                    showProgressBar();
                    getDataFromServer(query);
                    mSearchView.clearFocus();
                } else {
                    showNoConnectionMessage();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showNoConnectionMessage() {
        Toast.makeText(this, R.string.no_connection, Toast.LENGTH_SHORT).show();
    }

    private void getDataFromServer(String query) {
        mService = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelCharacter>> responseCall = mService.getCharacters(query, API_KEY, 1, HASH);

        responseCall.enqueue(new Callback<BaseResponse<MarvelCharacter>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<MarvelCharacter>> call, @NonNull Response<BaseResponse<MarvelCharacter>> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null) {
                    hideProgressBar();
                    showData(new ArrayList<>(baseResponse.getResponseData().getCharacterList()));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelCharacter>> call, Throwable t) {
                Log.e(TAG, "failure with message" + t.getMessage());
            }
        });
    }

    private void showData(ArrayList<MarvelCharacter> characters) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, CharacterFragment.newInstance(characters))
                .addToBackStack(CharacterFragment.TAG)
                .commit();
    }

    //endregion
}
