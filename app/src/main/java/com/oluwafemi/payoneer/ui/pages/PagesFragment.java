package com.oluwafemi.payoneer.ui.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oluwafemi.payoneer.databinding.PagesFragmentBinding;
import com.oluwafemi.payoneer.ui.factory.adapter.UIFactoryAdapter;
import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import java.util.ArrayList;
import java.util.List;

public class PagesFragment extends ActionFragment {

    private static final String TAG = PagesFragment.class.getSimpleName();

    protected PagesFragmentBinding pagesFragmentBinding;
    protected UIFactoryAdapter factoryAdapter = new UIFactoryAdapter(new ArrayList<>(), null);

    public PagesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        pagesFragmentBinding = PagesFragmentBinding.inflate(inflater, container, false);
        return pagesFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(pagesFragmentBinding.rvPages);
    }

    private void setupRecyclerView(RecyclerView rvPages) {
        rvPages.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvPages.setAdapter(factoryAdapter);
    }

    protected void updateUI(List<UIField> uiFields) {
        factoryAdapter.updateList(uiFields);
    }

    protected void setEventListener(FactoryEventListener factoryEventListener) {
        factoryAdapter.setFactoryEventListener(factoryEventListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pagesFragmentBinding = null;
    }
}
