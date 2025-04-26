package com.example.tuto.exception.handler;

import com.example.tuto.exception.custom.BusinessException;
import com.example.tuto.exception.dto.ErrorDetails;
import com.example.tuto.exception.handler.custom.ResourceNotFoundException;

import lombok.Getter;

import com.example.tuto.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;


@RestControllerAdvice
@Getter
public class GlobalExceptionHandler {

    private ErrorCode errorCode;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(
        ResourceNotFoundException ex, WebRequest request) {
        
        ErrorDetails errorDetails = ErrorDetails.builder()
            .timestamp(LocalDateTime.now())
            .message(ex.getMessage())
            .status(HttpStatus.NOT_FOUND)
            .path(request.getDescription(false))
            .build();
        
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDetails> handleBusinessException(
        BusinessException ex, WebRequest request) {
        
        ErrorDetails errorDetails = ErrorDetails.builder()
            .timestamp(LocalDateTime.now())
            .message(ex.getMessage())
            .status(HttpStatus.BAD_REQUEST)
            .path(request.getDescription(false))
            .build();
        
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}