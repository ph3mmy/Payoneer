package com.oluwafemi.data.exception;

import java.io.IOException;

public class ServerErrorException extends IOException {
    public ServerErrorException(String message) {
        super(message);
    }
}
