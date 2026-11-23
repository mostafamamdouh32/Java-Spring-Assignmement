package com.example.NileMart.exception;

public class OutOfStockException extends StoreException {
    public OutOfStockException(String message) {
        super(message);
    }
    public OutOfStockException(String message ,  Throwable cause) {
        super(message,cause);
    }
    @Override
    public int status(){
        return 409;
    }
}
