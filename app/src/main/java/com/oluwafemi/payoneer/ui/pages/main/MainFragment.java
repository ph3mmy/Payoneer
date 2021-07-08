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
import android.widget.Toast;

import com.google.gson.Gson;
import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.payoneer.R;
import com.oluwafemi.payoneer.databinding.MainFragmentBinding;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;
import com.oluwafemi.payoneer.ui.factory.model.ImageLabelField;
import com.oluwafemi.payoneer.ui.factory.model.UIField;
import com.oluwafemi.payoneer.ui.pages.PagesFragment;
import com.oluwafemi.payoneer.ui.vmfactory.PaymentVMFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainFragment extends PagesFragment {

    private MainViewModel mViewModel;
    public static final String TAG = MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this, new PaymentVMFactory()).get(MainViewModel.class);
        mViewModel.uiStateLiveData.observe(getViewLifecycleOwner(), this::updateUI);

        mViewModel.loadPaymentNetworks();
        setupClickListener();
    }

    private void setupClickListener() {
        setEventListener((uiField, param) -> {
            if (uiField instanceof ImageLabelField) {
                PaymentNetwork network = (PaymentNetwork) uiField.fieldDataSource();
            }
        });
    }

    private void updateUI(UIState<List<UIField>> uiState) {
        switch (uiState.status) {
            case LOADING:
                break;
            case ERROR:
                Log.e(TAG, "error: " + new Gson().toJson(uiState.error.getMessage()));
                break;
            case SUCCESS:
                updateUI(uiState.data);
                break;
            default:
                break;
        }
    }

}