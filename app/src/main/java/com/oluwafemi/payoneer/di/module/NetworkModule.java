package com.oluwafemi.payoneer.di.module;

import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.data.network.interceptor.NetworkInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String API_BASE_URL = "https://raw.githubusercontent.com/";
    private static final long REQUEST_TIMEOUT = 30L;

    @Provides
    public PaymentApi providePaymentApi(Retrofit retrofit) {
        return retrofit.create(PaymentApi.class);
    }

    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient providesOkHttpClient(NetworkInterceptor networkInterceptor) {

        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(networkInterceptor);
        okhttpClientBuilder.addInterceptor(chain -> {

            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        return okhttpClientBuilder.build();
    }
}
