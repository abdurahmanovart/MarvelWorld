package com.github.abdurahmanovart.marvelworld.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Abdurakhmanov on 12.09.17
 */

public class ApiClient {
    public static final String BASE_URL = "http://gateway.marvel.com/";
    private static Retrofit sRetrofit;

    private ApiClient() {
        throw new IllegalStateException("can't create object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
