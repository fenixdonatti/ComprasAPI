package com.estudo.api.error.product;

public class ProductCreateException extends Exception {
    public ProductCreateException(String message) {
        super(message);
    }

    public ProductCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}