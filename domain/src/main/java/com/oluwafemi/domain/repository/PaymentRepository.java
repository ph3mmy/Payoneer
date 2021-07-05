package com.oluwafemi.domain.repository;

import com.oluwafemi.domain.PaymentNetwork;
import java.util.List;
import io.reactivex.Observable;

public interface PaymentRepository {

    Observable<List<PaymentNetwork>> paymentNetworks();

}
