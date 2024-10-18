package com.example.projectbase.exception;

public class MaxQuestionsSentException extends RuntimeException {
    public MaxQuestionsSentException(String message) {
        super(message);
    }
}
