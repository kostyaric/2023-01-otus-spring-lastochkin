package ru.otus.task05.localisation;

public interface LocaleMessage {
    String getLoclisedMessage(String messageKey);
    String getLoclisedMessage(String messageKey, String[] params);
}
