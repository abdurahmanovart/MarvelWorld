package com.github.abdurahmanovart.marvelworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.abdurahmanovart.marvelworld.bean.BaseResponse;
import com.github.abdurahmanovart.marvelworld.net.ApiClient;
import com.github.abdurahmanovart.marvelworld.net.MarvelService;

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
        String name = "hul";

        MarvelService service = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse> responseCall = service.getCharacters(name, API_KEY, 123, HASH);

        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null) {
                    System.out.println(baseResponse.toString());
                } else {
                    System.out.println("it is null");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
