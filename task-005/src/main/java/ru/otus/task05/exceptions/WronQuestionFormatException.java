package ru.otus.task05.exceptions;

public class WronQuestionFormatException extends RuntimeException{
    public WronQuestionFormatException(String message) {
        super(message);
    }
}
