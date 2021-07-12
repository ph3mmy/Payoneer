package com.oluwafemi.payoneer.ui.pages.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.payoneer.databinding.MainFragmentBinding;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.adapter.UIFactoryAdapter;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding fragmentBinding;
    private UIFactoryAdapter factoryAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentBinding = MainFragmentBinding.inflate(inflater, container, false);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.uiStateLiveData.observe(getViewLifecycleOwner(), this::updateUI);

        mViewModel.loadPaymentNetworks();
        clickListeners();
    }

    private void clickListeners() {
        fragmentBinding.errorLayout.btnTryAgain.setOnClickListener(view ->
                mViewModel.loadPaymentNetworks()
        );
    }

    private void setUpView() {
        factoryAdapter = new UIFactoryAdapter(new ArrayList<>(), null);
        fragmentBinding.rvNetworks.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
        );
        fragmentBinding.rvNetworks.setAdapter(factoryAdapter);
    }

    private void updateUI(UIState<List<PaymentNetwork>> uiState) {
        switch (uiState.getStatus()) {
            case LOADING:
                showProgress();
                break;
            case ERROR:
                showError(uiState.getError().getMessage());
                break;
            case SUCCESS:
                List<UIField> fields = mViewModel.uiFieldsFromPaymentNetworks(uiState.getData());
                updateAdapter(fields);
                showRecycler();
                break;
            default:
                break;
        }
    }


    private void showRecycler() {
        fragmentBinding.progressView.setVisibility(View.GONE);
        fragmentBinding.rvNetworks.setVisibility(View.VISIBLE);
        fragmentBinding.errorLayout.errorMain.setVisibility(View.GONE);
    }

    private void showProgress() {
        fragmentBinding.progressView.setVisibility(View.VISIBLE);
        fragmentBinding.rvNetworks.setVisibility(View.GONE);
        fragmentBinding.errorLayout.errorMain.setVisibility(View.GONE);
    }

    private void showError(String errorMessage) {
        fragmentBinding.progressView.setVisibility(View.GONE);
        fragmentBinding.rvNetworks.setVisibility(View.GONE);
        fragmentBinding.errorLayout.errorMain.setVisibility(View.VISIBLE);

        String formattedError = String.format("Error:\n %s", errorMessage);
        fragmentBinding.errorLayout.tvError.setText(formattedError);
    }

    protected void updateAdapter(List<UIField> uiFields) {
        factoryAdapter.updateList(uiFields);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentBinding = null;
    }
}