package com.oluwafemi.payoneer.ui.pages.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oluwafemi.data.network.util.NetworkEvent;
import com.oluwafemi.domain.PaymentNetwork;
import com.oluwafemi.payoneer.databinding.MainFragmentBinding;
import com.oluwafemi.payoneer.ui.factory.UIState;
import com.oluwafemi.payoneer.ui.factory.adapter.UIFactoryAdapter;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();
    private MainViewModel mViewModel;
    private MainFragmentBinding fragmentBinding;
    private UIFactoryAdapter factoryAdapter;

    @Inject
    NetworkEvent networkEvent;

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

    @Override
    public void onResume() {
        super.onResume();
        registerNetworkEvent();
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
                showError(uiState.getError());
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

    private void showError(@Nullable Throwable error) {
        fragmentBinding.progressView.setVisibility(View.GONE);
        fragmentBinding.rvNetworks.setVisibility(View.GONE);
        fragmentBinding.errorLayout.errorMain.setVisibility(View.VISIBLE);

        if (error != null) {
            String errorMessage = error.getMessage();
            fragmentBinding.errorLayout.tvError.setText(errorMessage);
        }
    }

    protected void updateAdapter(List<UIField> uiFields) {
        factoryAdapter.updateList(uiFields);
    }

    public void registerNetworkEvent() {
        networkEvent.register(this,
                networkState -> {
                    switch (networkState) {
                        case NO_INTERNET:
                            Log.e(TAG, "registerNetworkEvent: NO_INTERNET");
                            break;
                        case NO_RESPONSE:
                            Log.e(TAG, "registerNetworkEvent: NO_RESPONSE");
                            break;

                        case UNAUTHORISED:
                            Log.e(TAG, "registerNetworkEvent: UNAUTHORIZED");
                            break;
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentBinding = null;
        networkEvent.unregister(this);
    }
}