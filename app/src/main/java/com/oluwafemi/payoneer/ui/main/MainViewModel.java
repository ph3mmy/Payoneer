package com.oluwafemi.payoneer.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public static final String TAG = MainViewModel.class.getSimpleName();
    private final PaymentNetworkUseCase paymentNetworkUseCase;
    private final CompositeDisposable compositeDisposable;
    public MutableLiveData<List<PaymentNetwork>> paymentNetworkLiveData;

    public MainViewModel(PaymentNetworkUseCase paymentNetworkUseCase) {
        this.paymentNetworkUseCase = paymentNetworkUseCase;
        this.compositeDisposable = new CompositeDisposable();
        this.paymentNetworkLiveData = new MutableLiveData<>();
    }

    public void loadPaymentNetworks() {
        compositeDisposable.add(
                paymentNetworkUseCase.paymentNetworks()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                paymentNetworks -> {
                                    paymentNetworkLiveData.postValue(paymentNetworks);
                                },
                                throwable -> {
                                    Log.e(TAG, "loadPaymentNetworks: Error == " + throwable.getMessage() );
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}