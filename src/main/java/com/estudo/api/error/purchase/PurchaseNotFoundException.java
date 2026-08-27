package com.estudo.api.error.purchase;

public class PurchaseNotFoundException extends Exception{
    public PurchaseNotFoundException(String message) {
        super(message);
    }
}
