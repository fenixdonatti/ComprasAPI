package com.estudo.api.error.user;

public class UserCreateException extends Exception {
    public UserCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
