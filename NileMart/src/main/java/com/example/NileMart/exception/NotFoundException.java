package com.example.NileMart.exception;


public class NotFoundException extends StoreException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message,cause);
    }
    @Override
    public int status(){
               return 404;
    }
}
