package com.dio.sacolaapi.handler;

public class SacolaException extends RuntimeException{

    private final long serialVersionUID = 1L;

    public SacolaException(String message) {
        super(message);
    }
}
