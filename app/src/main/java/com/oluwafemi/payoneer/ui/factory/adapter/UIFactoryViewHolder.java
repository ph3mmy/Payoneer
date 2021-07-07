package com.oluwafemi.payoneer.ui.factory.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;
import com.oluwafemi.payoneer.ui.factory.model.UIField;
import org.jetbrains.annotations.NotNull;

public class UIFactoryViewHolder extends RecyclerView.ViewHolder {

    public UIFactoryViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
    }

    public void bind(UIField uiField, FactoryEventListener factoryEventListener) {
        uiField.bind(itemView, factoryEventListener);
    }
}
