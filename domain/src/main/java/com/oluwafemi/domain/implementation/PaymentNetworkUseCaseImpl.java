package com.oluwafemi.domain.implementation;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.repository.PaymentRepository;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PaymentNetworkUseCaseImpl implements PaymentNetworkUseCase {

    private final PaymentRepository paymentRepository;

    @Inject
    public PaymentNetworkUseCaseImpl(PaymentRepository repository) {
        this.paymentRepository = repository;
    }

    @Override
    public Observable<List<PaymentNetwork>> paymentNetworks() {
        return paymentRepository.paymentNetworks();
    }
}
