package com.estudo.api.error.user;

public class UserDeleteException extends Exception {
    public UserDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
