package com.estudo.api.error.product;

public class ProductDeleteException extends Exception {
    public ProductDeleteException(String message) {
        super(message);
    }

    public ProductDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}