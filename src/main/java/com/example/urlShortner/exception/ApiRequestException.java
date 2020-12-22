package com.example.urlShortner.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
    private final HttpStatus httpStatus;
    public ApiRequestException(String message, HttpStatus status){
        super(message);
        this.httpStatus = status;
    }

    public ApiRequestException(String message,  HttpStatus status ,Throwable cause){
        super(message, cause);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
