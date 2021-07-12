package com.oluwafemi.payoneer.util;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PayoneerScheduler {

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler androidMain() {
        return AndroidSchedulers.mainThread();
    }

}
