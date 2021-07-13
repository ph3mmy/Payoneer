package com.oluwafemi.data.exception;

import java.io.IOException;

public class UnAuthorizedException extends IOException {

    public UnAuthorizedException(String message) {
        super(message);
    }
}
