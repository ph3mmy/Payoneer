package com.oluwafemi.payoneer.ui.factory;

public class UIState<T> {

    public enum Status {
        LOADING,
        ERROR,
        SUCCESS
    }

    public final T data;
    public Throwable error;
    public Status status;

    public UIState(Status status, T data, Throwable error) {
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public UIState<T> success(T data) {
        return new UIState<>(Status.SUCCESS, data, null);
    }

    public UIState<T> error(Throwable error) {
        return new UIState<>(Status.ERROR, null, error);
    }

    public UIState<T> loading() {
        return new UIState<>(Status.LOADING, null, null);
    }
}
