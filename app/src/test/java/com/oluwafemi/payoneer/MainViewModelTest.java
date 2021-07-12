package com.oluwafemi.payoneer;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.model.ImageLabelField;
import com.oluwafemi.payoneer.ui.factory.model.UIField;
import com.oluwafemi.payoneer.ui.pages.main.MainViewModel;
import com.oluwafemi.payoneer.util.PayoneerScheduler;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MainViewModel mainViewModel;
    private TestScheduler testScheduler;
    @Mock private PaymentNetworkUseCase paymentNetworkUseCase;
    @Mock private PayoneerScheduler payoneerScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> testScheduler);

        Mockito.when(payoneerScheduler.io()).thenReturn(Schedulers.trampoline());
        Mockito.when(payoneerScheduler.androidMain()).thenReturn(Schedulers.trampoline());
        mainViewModel = new MainViewModel(paymentNetworkUseCase, payoneerScheduler);
    }

    @Test
    public void uiState_is_not_null_with_a_null_response() {
        Mockito.when(paymentNetworkUseCase.paymentNetworks())
                .thenReturn(null);

        assertNotNull(mainViewModel.uiStateLiveData);
    }

    @Test
    public void uiState_returns_success_when_request_is_successful() {

        List<PaymentNetwork> networks = new ArrayList<>();

        Mockito.when(paymentNetworkUseCase.paymentNetworks())
                .thenReturn(Observable.just(networks));

        mainViewModel.loadPaymentNetworks();

        mainViewModel.uiStateLiveData.observeForever(result -> {
            if (result.getStatus() == UIState.Status.SUCCESS) {
                Assert.assertNotNull(result.getData());
            }
        });
    }

    @Test
    public void uiState_returns_valid_list_item_when_request_is_successful() {

        List<PaymentNetwork> networks = createFakeNetworks();

        Mockito.when(paymentNetworkUseCase.paymentNetworks())
                .thenReturn(Observable.just(networks));

        mainViewModel.loadPaymentNetworks();

        mainViewModel.uiStateLiveData.observeForever(result -> {
            if (result.getStatus() == UIState.Status.SUCCESS) {
                Assert.assertEquals(result.getData().size(), networks.size());
            }
        });
    }

    @Test
    public void uiState_returns_error_when_request_is_fails() {

        Throwable error = new Throwable("Request Timeout");

        Mockito.when(paymentNetworkUseCase.paymentNetworks())
                .thenReturn(Observable.error(error));

        mainViewModel.loadPaymentNetworks();

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        mainViewModel.uiStateLiveData.observeForever(result -> {
            if (result.getStatus() == UIState.Status.ERROR) {
                Assert.assertEquals(result.getError(), error);
            }
        });
    }

    @Test
    public void uiFieldsFromPayment_returns_valid_no_of_imageFields() {
        List<PaymentNetwork> testNetworks = createFakeNetworks();
        List<UIField> uiFields = mainViewModel.uiFieldsFromPaymentNetworks(testNetworks);

        assertThat(uiFields.toArray()[0], Matchers.is(Matchers.instanceOf(ImageLabelField.class)));
        assertEquals(2, uiFields.size());
    }

    private List<PaymentNetwork> createFakeNetworks() {
        List<PaymentNetwork> networks = new ArrayList<>();
        PaymentNetwork network1 = new PaymentNetwork();
        network1.setCode("VISA");
        network1.setLabel("Visa");
        network1.setMethod("CREDIT_CARD");
        network1.setLogoUrl("http://visa.com/logo");
        networks.add(network1);

        PaymentNetwork network2 = new PaymentNetwork();
        network2.setCode("UNIONPAY");
        network2.setLabel("UnionPay");
        network2.setMethod("CREDIT_CARD");
        network2.setLogoUrl("http://union.com/logo");
        networks.add(network2);

        return networks;
    }

}
