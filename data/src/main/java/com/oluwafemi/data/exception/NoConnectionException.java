package com.oluwafemi.data.exception;

import java.io.IOException;

public class NoConnectionException extends IOException {
    public NoConnectionException(String message) {
        super(message);
    }
}
