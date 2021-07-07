package com.oluwafemi.payoneer.ui.pages.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.payoneer.R;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.vmfactory.PaymentVMFactory;

import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    public static final String TAG = MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new PaymentVMFactory()).get(MainViewModel.class);
        mViewModel.uiStateLiveData.observe(getViewLifecycleOwner(), this::updateUI);

        mViewModel.loadPaymentNetworks();
    }

    private void updateUI(UIState<List<PaymentNetwork>> uiState) {
        switch (uiState.status) {
            case LOADING:
                break;
            case ERROR:
                Log.e(TAG, "error: " + new Gson().toJson(uiState.error.getMessage()));
                break;
            case SUCCESS:
                Log.e(TAG, "success: list size == " + new Gson().toJson(uiState.data));
                break;
            default:
                break;
        }
    }

}