package com.oluwafemi.data.exception;

import java.io.IOException;

public class NoResponseException extends IOException {
    public NoResponseException(String message) {
        super(message);
    }
}
