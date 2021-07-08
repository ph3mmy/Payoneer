package com.oluwafemi.payoneer.ui.factory.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;
import com.oluwafemi.payoneer.ui.factory.model.UIField;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UIFactoryAdapter extends RecyclerView.Adapter<UIFactoryViewHolder> {

    private final List<UIField> uiFields;
    private FactoryEventListener factoryEventListener;

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

    public void updateList(List<UIField> uiFields) {
        this.uiFields.clear();
        this.uiFields.addAll(uiFields);
        notifyDataSetChanged();
    }

    public void setFactoryEventListener(FactoryEventListener factoryEventListener) {
        this.factoryEventListener = factoryEventListener;
    }

    public UIField fieldWithKey(String key) {
        UIField uiField = null;
        for (UIField field : uiFields) {
            if (field.fieldKey().equalsIgnoreCase(key)) {
                uiField = field;
                break;
            }
        }
        return uiField;
    }

    public int fieldIndexWithKey(String key) {
        int index = -1;
        for (int i = 0; i < uiFields.size(); i++) {
            if (uiFields.get(i).fieldKey().equalsIgnoreCase(key)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int fieldIndex(UIField uiField) {
        int index = -1;
        for (int i = 0; i < uiFields.size(); i++) {
            if (uiFields.get(i).fieldKey().equalsIgnoreCase(uiField.fieldKey())) {
                index = i;
                break;
            }
        }
        return index;
    }

}
