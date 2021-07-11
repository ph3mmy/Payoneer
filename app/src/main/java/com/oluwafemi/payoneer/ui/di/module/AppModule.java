package com.oluwafemi.payoneer.ui.di.module;

import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.data.repository.PaymentNetworkRepository;
import com.oluwafemi.domain.implementation.PaymentNetworkUseCaseImpl;
import com.oluwafemi.domain.repository.PaymentRepository;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

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
