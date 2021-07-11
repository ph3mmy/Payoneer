package com.oluwafemi.data;

import com.oluwafemi.data.entity.mapper.PaymentEntityMapper;
import com.oluwafemi.data.network.api.PaymentApi;
import com.oluwafemi.data.repository.PaymentNetworkRepository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PaymentNetworkRepositoryTest {

    private PaymentNetworkRepository networkRepository;
    @Mock private PaymentEntityMapper entityMapper;
    @Mock private PaymentApi paymentApi;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        networkRepository = new PaymentNetworkRepository(paymentApi, entityMapper);
    }

}
