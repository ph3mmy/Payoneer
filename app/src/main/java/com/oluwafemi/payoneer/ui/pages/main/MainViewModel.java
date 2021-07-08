package com.oluwafemi.payoneer.ui.pages.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.model.ImageLabelField;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public static final String TAG = MainViewModel.class.getSimpleName();
    private final PaymentNetworkUseCase paymentNetworkUseCase;
    private final CompositeDisposable compositeDisposable;
    public MutableLiveData<UIState<List<UIField>>> uiStateLiveData;

    public MainViewModel(PaymentNetworkUseCase paymentNetworkUseCase) {
        this.paymentNetworkUseCase = paymentNetworkUseCase;
        this.compositeDisposable = new CompositeDisposable();
        this.uiStateLiveData = new MutableLiveData<>();
    }

    public void loadPaymentNetworks() {
        compositeDisposable.add(
                paymentNetworkUseCase.paymentNetworks()
                        .map(paymentNetworks -> {
                            List<UIField> uiFields = uiFieldsFromPaymentNetworks(paymentNetworks);
                           return new UIState<>(UIState.Status.SUCCESS, uiFields, null);
                        })
                        .onErrorReturn(error -> new UIState<>(UIState.Status.ERROR, null, error))
                        .startWith(new UIState<>(UIState.Status.LOADING, null, null))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(uiState ->
                                uiStateLiveData.postValue(uiState)
                        )
        );
    }

    private List<UIField> uiFieldsFromPaymentNetworks(List<PaymentNetwork> paymentNetworks) {
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