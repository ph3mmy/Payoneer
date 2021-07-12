package com.oluwafemi.payoneer.ui.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UIState<T> {

    @NonNull
    private Status status;

    @Nullable
    private T data;

    @Nullable
    private Throwable error;

    public UIState<T> loading() {
        this.status = Status.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public UIState<T> success(@NonNull T data) {
        this.status = Status.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public UIState<T> error(@NonNull Throwable error) {
        this.status = Status.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
