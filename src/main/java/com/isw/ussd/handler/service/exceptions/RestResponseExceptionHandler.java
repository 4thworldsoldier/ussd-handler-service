package com.isw.ussd.handler.service.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isw.ussd.handler.service.payload.BvnUssdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends RuntimeException {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Error reading the server response: " + ex.getMessage();
        // Log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("4xx");
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }

    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity<Object> handleNullPointerException(HttpMessageNotReadableException ex) {
        String errorMessage = "Error a required field is null : " + ex.getMessage();
        // Log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("4xx");
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class,IndexOutOfBoundsException.class})
    protected ResponseEntity<Object> handleOutOfBoundsException(HttpMessageNotReadableException ex) {
        String errorMessage = "An out of bounds error occured : " + ex.getMessage();
        // Log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("4xx");
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<Object> handleResourceAccessException(ResourceAccessException ex) {
        String errorMessage = "Network or I/O Error: " + ex.getMessage();
        //log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("5xx");
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {
        String errorMessage = "Error occurred during JSON processing: " + ex.getMessage();
        // You can log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("4xx");

        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }


    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        String errorMessage = "Error client server returned Http status 4xx : " + ex.getMessage();
        //  log the error here if needed
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("4xx");
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }


    // Default exception handler for any other exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception ex) {
        String errorMessage = "An error has occurred: " + ex.getMessage();
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();

        bvnUssdResponse.setErrorMessage(errorMessage);
        bvnUssdResponse.setSuccess(false);
        bvnUssdResponse.setErrorCode("5xx");
        // You can log the error here if needed
        return new ResponseEntity<>(bvnUssdResponse, HttpStatus.OK);
    }
}
