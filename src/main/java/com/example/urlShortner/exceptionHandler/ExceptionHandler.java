package com.example.urlShortner.exceptionHandler;

import com.example.urlShortner.exception.ApiException;
import com.example.urlShortner.exception.ApiRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        ApiException apiException = new ApiException("Something Went Wrong",
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRquestException(ApiRequestException ex, WebRequest request){

        ApiException  apiException = new ApiException(
                ex.getMessage(),
                ex.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return  new ResponseEntity<>(apiException, ex.getHttpStatus());
    }
}
