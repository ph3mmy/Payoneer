package com.oluwafemi.data.repository;

import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.RetrofitClient;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.repository.PaymentRepository;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PaymentNetworkRepository implements PaymentRepository {

    private final PaymentApi paymentApi;
    private final PaymentEntityMapper paymentEntityMapper;

    public PaymentNetworkRepository(RetrofitClient retrofitClient, PaymentEntityMapper mapper) {
        this.paymentEntityMapper = mapper;
        paymentApi = retrofitClient.create(PaymentApi.class);
    }

    @Override
    public Observable<List<PaymentNetwork>> paymentNetworks() {
        return paymentApi.getPaymentNetworks().map(this.paymentEntityMapper::transform);
    }
}
