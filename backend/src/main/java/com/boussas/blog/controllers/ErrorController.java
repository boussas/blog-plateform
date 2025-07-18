package com.boussas.blog.controllers;


import com.boussas.blog.entities.dtos.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> HandleException(Exception ex) {
        ApiErrorResponse error = ApiErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error Occurred")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
            ApiErrorResponse error= ApiErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Bad Request Error Occurred "+ ex.getMessage())
                    .build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalArgumentException ex) {
        ApiErrorResponse error= ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message("Bad Request Error Occurred "+ ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect username or password")
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
