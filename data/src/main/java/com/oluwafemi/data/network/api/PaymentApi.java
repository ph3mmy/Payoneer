package com.oluwafemi.data.network.api;

import com.oluwafemi.data.entity.PaymentNetworkEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PaymentApi {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Observable<PaymentNetworkEntity> getPaymentNetworks();

}
