package com.raviteja.springframwork.BeerOrderService.Exceptions;

public class BeerOrderException extends RuntimeException{

    private final String message;

    public BeerOrderException(String message) {
        this.message = message;
    }
}
