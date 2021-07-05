package com.oluwafemi.domain.usecase;

import com.oluwafemi.domain.PaymentNetwork;

import java.util.List;

import io.reactivex.Observable;

public interface PaymentNetworkUseCase {

    Observable<List<PaymentNetwork>> paymentNetworks();

}
