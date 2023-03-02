package ru.otus.task05.exceptions;

public class ReadQuestionsException extends RuntimeException{
    public ReadQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
