package com.oluwafemi.payoneer.ui.pages.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.model.ImageLabelField;
import com.oluwafemi.payoneer.ui.factory.model.UIField;
import com.oluwafemi.payoneer.util.PayoneerScheduler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final PaymentNetworkUseCase paymentNetworkUseCase;
    private final CompositeDisposable compositeDisposable;
    public MutableLiveData<UIState<List<PaymentNetwork>>> uiStateLiveData;
    private final PayoneerScheduler scheduler;

    @Inject
    public MainViewModel(PaymentNetworkUseCase paymentNetworkUseCase, PayoneerScheduler scheduler) {
        this.paymentNetworkUseCase = paymentNetworkUseCase;
        this.compositeDisposable = new CompositeDisposable();
        this.uiStateLiveData = new MutableLiveData<>();
        this.scheduler = scheduler;
    }

    public void loadPaymentNetworks() {
        compositeDisposable.add(
                paymentNetworkUseCase.paymentNetworks()
                        .map(paymentNetworks -> new UIState<List<PaymentNetwork>>().success(paymentNetworks))
                        .onErrorReturn(error -> new UIState<List<PaymentNetwork>>().error(error))
                        .startWith(new UIState<List<PaymentNetwork>>().loading())
                        .subscribeOn(scheduler.io())
                        .observeOn(scheduler.androidMain())
                        .subscribe(uiState ->
                                uiStateLiveData.postValue(uiState)
                        )
        );
    }

    public List<UIField> uiFieldsFromPaymentNetworks(List<PaymentNetwork> paymentNetworks) {
        ArrayList<UIField> fieldArrayList = new ArrayList<>();
        for (PaymentNetwork network : paymentNetworks) {
            fieldArrayList.add(
                    new ImageLabelField(network.getLabel(), network.getLogoUrl())
                            .withDataSource(network)
            );
        }
        return fieldArrayList;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}