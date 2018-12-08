package com.mall.common.exception;


public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, 404);
    }

    public NotFoundException() {
        this("data not found");
    }
}
