package ru.otus.task05;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.task05.processor.QuestionService;

@Component
public class AppRunner implements CommandLineRunner {

    private final QuestionService questionService;

    public AppRunner(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) throws Exception {
        questionService.startProcess();
    }
}
