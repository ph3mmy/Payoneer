package com.oluwafemi.data.repository;

import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.repository.PaymentRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PaymentNetworkRepository implements PaymentRepository {

    private final PaymentApi paymentApi;
    private final PaymentEntityMapper paymentEntityMapper;

    @Inject
    public PaymentNetworkRepository(PaymentApi paymentApi, PaymentEntityMapper paymentEntityMapper) {
        this.paymentEntityMapper = paymentEntityMapper;
        this.paymentApi = paymentApi;
    }

    @Override
    public Observable<List<PaymentNetwork>> paymentNetworks() {
        return paymentApi.getPaymentNetworks().map(this.paymentEntityMapper::transform);
    }
}
