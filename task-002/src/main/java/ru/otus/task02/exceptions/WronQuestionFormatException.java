package ru.otus.task02.exceptions;

public class WronQuestionFormatException extends RuntimeException{
    public WronQuestionFormatException(String message) {
        super(message);
    }
}
