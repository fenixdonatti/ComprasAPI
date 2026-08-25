package com.estudo.api.error;

public class UserUpdateException extends Exception {
    public UserUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
