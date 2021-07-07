package com.oluwafemi.payoneer.ui.vmfactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.oluwafemi.data.repository.PaymentNetworkRepository;
import com.oluwafemi.domain.implementation.PaymentNetworkUseCaseImpl;
import com.oluwafemi.domain.repository.PaymentRepository;
import com.oluwafemi.domain.usecase.PaymentNetworkUseCase;
import com.oluwafemi.payoneer.ui.pages.main.MainViewModel;

import org.jetbrains.annotations.NotNull;

public class PaymentVMFactory extends ViewModelProvider.NewInstanceFactory {

    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            PaymentRepository paymentRepository = new PaymentNetworkRepository();
            PaymentNetworkUseCase paymentNetworkUseCase = new PaymentNetworkUseCaseImpl(paymentRepository);
            return (T) new MainViewModel(paymentNetworkUseCase);
        } else {
            throw new IllegalArgumentException("ViewModel - " + modelClass.getSimpleName() + "not found");
        }
    }

}
