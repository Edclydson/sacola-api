package com.dio.sacolaapi.handler;

public class SacolaVaziaException extends RuntimeException{

        private final long serialVersionUID = 1L;

        public SacolaVaziaException(String message){
            super(message);
        }
}
