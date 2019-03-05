package com.mall.forum.core.exceptions;


public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -3577897862011575132L;

    public ValidationException(String message) {
        super(message);
    }
}
