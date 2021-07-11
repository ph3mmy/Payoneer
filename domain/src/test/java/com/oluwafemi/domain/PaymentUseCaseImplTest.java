package com.oluwafemi.domain;

import com.oluwafemi.domain.implementation.PaymentNetworkUseCaseImpl;
import com.oluwafemi.domain.repository.PaymentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import io.reactivex.Observable;

@RunWith(JUnit4.class)
public class PaymentUseCaseImplTest {

    private PaymentNetworkUseCaseImpl paymentNetworkUseCase;
    @Mock
    private PaymentRepository mockPaymentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        paymentNetworkUseCase = new PaymentNetworkUseCaseImpl(mockPaymentRepository);
    }

    @Test
    public void paymentUseCase_returns_paymentNetworks_when_successful() {
        Mockito.when(mockPaymentRepository.paymentNetworks())
                .thenReturn(Observable.just(DataFake.fakePaymentNetworks()));

        paymentNetworkUseCase.paymentNetworks()
                .test()
                .assertValue(DataFake.fakePaymentNetworks())
                .dispose();

        Mockito.verify(mockPaymentRepository).paymentNetworks();

    }

    @Test
    public void paymentUseCase_returns_no_value_when_an_error_occur() {
        Mockito.when(mockPaymentRepository.paymentNetworks())
                .thenReturn(Observable.error(new IOException()));

        paymentNetworkUseCase.paymentNetworks()
                .test()
                .assertNoValues()
                .dispose();

        Mockito.verify(mockPaymentRepository).paymentNetworks();
    }

}
