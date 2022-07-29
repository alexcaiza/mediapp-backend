package com.mitocode.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	private String ERROR_1 = "1";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
    	System.out.println("Method handleAllException()");
        ExceptionResponse er = new ExceptionResponse(ERROR_1,LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
    	System.out.println("Method handleModelNotFoundException()");
        ExceptionResponse er = new ExceptionResponse(ERROR_1, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	System.out.println("Method handleMethodArgumentNotValid()");
    	System.err.println(ex);

        String msg = ex.getBindingResult().getAllErrors().stream().map(
                e -> e.getDefaultMessage().concat(",")).collect(Collectors.joining());

        ExceptionResponse er = new ExceptionResponse(ERROR_1, LocalDateTime.now(), msg, request.getDescription(false));

        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
}