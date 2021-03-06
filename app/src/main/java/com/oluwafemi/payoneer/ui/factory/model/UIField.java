package com.oluwafemi.payoneer.ui.factory.model;

import android.view.View;

import androidx.annotation.Nullable;

import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;

public abstract class UIField {

    public int layout;
    private String key = "";
    private Object dataSource = new Object();
    private String errorMessage = "";

    public UIField(int layout) {
        this.layout = layout;
    }

    public UIField withKey(String key) {
        this.key = key;
        return this;
    }

    public UIField withDataSource(Object dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public UIField errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String fieldKey() {
        return this.key;
    }

    public String fieldError() {
        return this.errorMessage;
    }

    public Object fieldDataSource() {
        return this.dataSource;
    }

    public abstract void bind(View itemView, FactoryEventListener factoryEventListener);

    public abstract Boolean hasValidData();

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean isEqual = false;
        if (obj != null) {
            isEqual = ((UIField)obj).key.equals(this.key);
        }
        return isEqual;
    }
}
