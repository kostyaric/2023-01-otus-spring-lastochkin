package ru.otus.task05.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AnswersProperties.class, LocalisationProperties.class, QuestionProperties.class})
public class ApplicationConfiguration {
}
