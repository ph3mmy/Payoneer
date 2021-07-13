package com.oluwafemi.data.network.util;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

@Singleton
public class NetworkEvent {

    @Inject
    public NetworkEvent() {}

    private PublishSubject<NetworkState> subject;

    @NonNull
    private PublishSubject<NetworkState> getSubject() {
        if (subject == null) {
            subject = PublishSubject.create();
        }
        return subject;
    }

    private final Map<Object, CompositeDisposable> compositeDisposableMap = new HashMap<>();

    @NonNull
    private CompositeDisposable getCompositeSubscription(@NonNull Object object) {
        CompositeDisposable compositeSubscription = compositeDisposableMap.get(object);
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeDisposable();
            compositeDisposableMap.put(object, compositeSubscription);
        }
        return compositeSubscription;
    }

    public void publish(@NonNull NetworkState networkState) {
        new Handler(Looper.getMainLooper())
                .post(() -> getSubject().onNext(networkState));
    }

    public void register(@NonNull Object lifecycle, @NonNull Consumer<NetworkState> action) {
        Disposable disposable = getSubject().subscribe(action);
        getCompositeSubscription(lifecycle).add(disposable);
    }

    public void unregister(@NonNull Object lifecycle) {
        CompositeDisposable compositeSubscription = compositeDisposableMap.remove(lifecycle);
        if (compositeSubscription != null) {
            compositeSubscription.dispose();
        }
    }
}