package com.oluwafemi.data.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitClient {

    public static final String API_BASE_URL = "https://raw.githubusercontent.com/";

    public RetrofitClient() {
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit(okHttpClientBuilder()).create(serviceClass);
    }

    private Retrofit retrofit(OkHttpClient okHttpClientBuilder) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(okHttpClientBuilder)
                .build();
    }

    private OkHttpClient okHttpClientBuilder() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(interceptor);
        okhttpClientBuilder.addInterceptor(chain -> {

            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        return okhttpClientBuilder.build();
    }

}
