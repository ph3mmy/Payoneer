package com.oluwafemi.data.network;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitClient {

    public static final String API_BASE_URL = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/";

    public RetrofitClient() {}

    public <T> T create(Class<T> serviceClass) {
        return retrofit(okHttpClientBuilder()).create(serviceClass);
    }

    private OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    private Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(okHttpClientBuilder.build())
                .build();
    }

}
