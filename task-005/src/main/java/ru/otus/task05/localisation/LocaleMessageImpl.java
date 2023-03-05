package ru.otus.task05.localisation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.task05.config.LocalisationProperties;

@Component
public class LocaleMessageImpl implements LocaleMessage{

    private final MessageSource messageSource;
    private final LocalisationProperties localisationProperties;

    public LocaleMessageImpl(MessageSource messageSource, LocalisationProperties localisationProperties) {
        this.messageSource = messageSource;
        this.localisationProperties = localisationProperties;
    }

    @Override
    public String getLoclisedMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, localisationProperties.getLocale());
    }

    @Override
    public String getLoclisedMessage(String messageKey, String[] params) {
        return messageSource.getMessage(messageKey, params, localisationProperties.getLocale());
    }

}
