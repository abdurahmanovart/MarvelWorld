package com.github.abdurahmanovart.marvelworld.net;

import com.github.abdurahmanovart.marvelworld.bean.BaseResponse;
import com.github.abdurahmanovart.marvelworld.bean.MarvelCharacter;
import com.github.abdurahmanovart.marvelworld.bean.MarvelResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author Abdurakhmanov on 10.09.17
 */

public interface MarvelService {

    @GET("v1/public/characters")
    Call<BaseResponse<MarvelCharacter>> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                                      @Query("apikey") String apiKey,
                                                      @Query("ts") int timeStamp,
                                                      @Query("hash") String hash);

    @GET
    Call<BaseResponse<MarvelResource>> getMarvelResources(@Url String path,
                                                          @Query("ts") int timeStamp,
                                                          @Query("hash") String hash,
                                                          @Query("apikey") String apiKey);
}
