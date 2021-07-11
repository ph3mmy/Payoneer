package com.oluwafemi.payoneer.ui.di.module;

import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.data.repository.PaymentNetworkRepository;
import com.oluwafemi.domain.implementation.PaymentNetworkUseCaseImpl;
import com.oluwafemi.domain.repository.PaymentRepository;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public PaymentNetworkUseCase providesPaymentNetworkUseCase(
            PaymentRepository paymentRepository
    ) {
        return new PaymentNetworkUseCaseImpl(paymentRepository);
    }

    @Provides
    public PaymentRepository providesPaymentRepository(
            PaymentApi paymentApi,
            PaymentEntityMapper paymentEntityMapper
    ) {
        return new PaymentNetworkRepository(paymentApi, paymentEntityMapper);
    }
}
