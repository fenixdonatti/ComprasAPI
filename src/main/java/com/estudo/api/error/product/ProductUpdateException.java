package com.estudo.api.error.product;

public class ProductUpdateException extends Exception {
    public ProductUpdateException(String message) {
        super(message);
    }

    public ProductUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}