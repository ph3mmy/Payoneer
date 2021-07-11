package com.oluwafemi.data;

import com.oluwafemi.data.entity.PaymentNetworkEntity;
import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.data.repository.PaymentNetworkRepository;
import com.oluwafemi.domain.repository.PaymentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

@RunWith(JUnit4.class)
public class PaymentNetworkRepositoryTest {

    private PaymentRepository paymentRepository;
    @Mock private PaymentEntityMapper mockEntityMapper;
    @Mock private PaymentApi mockPaymentApi;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRepository = new PaymentNetworkRepository(mockPaymentApi, mockEntityMapper);
    }

    @Test
    public void paymentRepository_invokes_paymentNetworkEntity_when_called() {
        PaymentNetworkEntity paymentNetworkEntity = new PaymentNetworkEntity();
        Mockito.when(mockPaymentApi.getPaymentNetworks())
                .thenReturn(Observable.just(paymentNetworkEntity));

        paymentRepository.paymentNetworks();

        Mockito.verify(mockPaymentApi).getPaymentNetworks();

    }

}
