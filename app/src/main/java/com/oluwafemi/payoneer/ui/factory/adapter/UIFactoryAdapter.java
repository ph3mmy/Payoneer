package com.oluwafemi.payoneer.ui.factory.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UIFactoryAdapter extends RecyclerView.Adapter<UIFactoryViewHolder> {

    private final List<UIField> uiFields;
    private final FactoryEventListener factoryEventListener;

    public UIFactoryAdapter(List<UIField> uiFields, FactoryEventListener factoryEventListener) {
        this.uiFields = uiFields;
        this.factoryEventListener = factoryEventListener;
    }

    @NonNull
    @NotNull
    @Override
    public UIFactoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new UIFactoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UIFactoryViewHolder holder, int position) {
        holder.bind(uiFields.get(position), factoryEventListener);
    }

    @Override
    public int getItemCount() {
        return uiFields.size();
    }

    @Override
    public int getItemViewType(int position) {
        return uiFields.get(position).layout;
    }


}
