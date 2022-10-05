package com.globant.djimenez.pruebatecnica.config;

import com.globant.djimenez.pruebatecnica.dto.response.ErrorDTOResponse;
import com.globant.djimenez.pruebatecnica.exception.InvalidOperationException;
import com.globant.djimenez.pruebatecnica.exception.ResourceNotFoundException;
import com.globant.djimenez.pruebatecnica.exception.TransactionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class ExceptionManager extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTOResponse> defaultExceptionHandler(Exception e, WebRequest webRequest){
        ErrorDTOResponse errorResponse = new ErrorDTOResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTOResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e, WebRequest webRequest){
        ErrorDTOResponse errorResponse = new ErrorDTOResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDTOResponse> invalidOperationExceptionHandler(InvalidOperationException e, WebRequest webRequest){
        ErrorDTOResponse errorResponse = new ErrorDTOResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorDTOResponse> transactionExceptionHandler(TransactionException e, WebRequest webRequest){
        ErrorDTOResponse errorResponse = new ErrorDTOResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
