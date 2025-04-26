package com.example.tuto.exception.custom;

import com.example.tuto.enums.ErrorCode;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // Ajouter le getter manquant
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}

