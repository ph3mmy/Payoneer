package com.oluwafemi.domain.implementation;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.repository.PaymentRepository;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;

import java.util.List;

import io.reactivex.Observable;

public class PaymentNetworkUseCaseImpl implements PaymentNetworkUseCase {

    private final PaymentRepository paymentRepository;

    public PaymentNetworkUseCaseImpl(PaymentRepository repository) {
        this.paymentRepository = repository;
    }

    @Override
    public Observable<List<PaymentNetwork>> paymentNetworks() {
        return paymentRepository.paymentNetworks();
    }
}
