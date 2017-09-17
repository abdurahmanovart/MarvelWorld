package com.github.abdurahmanovart.marvelworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.abdurahmanovart.marvelworld.bean.BaseResponse;
import com.github.abdurahmanovart.marvelworld.bean.MarvelCharacter;
import com.github.abdurahmanovart.marvelworld.bean.MarvelResource;
import com.github.abdurahmanovart.marvelworld.net.ApiClient;
import com.github.abdurahmanovart.marvelworld.net.MarvelService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "181a9f0e6d0b25babcb3cdf1c7075dd5";
    public static final String HASH = "cc07634e8e625376a3054614629a51ef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResponse();
    }

    private void getResponse() {
        String name = "HuL";

        MarvelService service = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelCharacter>> responseCall = service.getCharacters(name, API_KEY, 123, HASH);

        responseCall.enqueue(new Callback<BaseResponse<MarvelCharacter>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelCharacter>> call, Response<BaseResponse<MarvelCharacter>> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null) {
                    System.out.println(baseResponse.toString());
                    getResource(baseResponse);
                } else {
                    System.out.println("it is null");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelCharacter>> call, Throwable t) {

            }
        });
    }

    private void getResource(final BaseResponse<MarvelCharacter> response) {
        List<MarvelCharacter> list = response.getResponseData().getCharacterList();
        MarvelCharacter character = list.get(0);
        String url = character.getComics().getComicList().get(0).getResourceUri();

        MarvelService service = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelResource>> responseCall = service.getMarvelResources(url, API_KEY, 123, HASH);

        responseCall.enqueue(new Callback<BaseResponse<MarvelResource>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelResource>> call, Response<BaseResponse<MarvelResource>> response) {
                BaseResponse<MarvelResource> baseResponse = response.body();
                if(baseResponse!=null){
                    System.out.println(baseResponse.getResponseData().toString());
                } else {
                    System.out.println("null it is");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelResource>> call, Throwable t) {

            }
        });
    }
}
