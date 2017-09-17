package com.github.abdurahmanovart.marvelworld.net;

import com.github.abdurahmanovart.marvelworld.bean.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Abdurakhmanov on 10.09.17
 */

public interface MarvelService {
    @GET("v1/public/characters")
    Call<BaseResponse> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                     @Query("apikey") String apiKey,
                                     @Query("ts") int timeStamp,
                                     @Query("hash") String hash);
}