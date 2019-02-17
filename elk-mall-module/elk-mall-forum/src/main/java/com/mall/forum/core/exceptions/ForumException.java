package com.mall.forum.core.exceptions;

public class ForumException extends RuntimeException {
    private static final long serialVersionUID = 4284828474973959205L;

    public ForumException(String message) {
        super(message);
    }

    public ForumException(Throwable t) {
        super(t);
        this.setStackTrace(t.getStackTrace());
    }

    public ForumException(String message, Throwable t) {
        super(message, t);
        this.setStackTrace(t.getStackTrace());
    }
}
