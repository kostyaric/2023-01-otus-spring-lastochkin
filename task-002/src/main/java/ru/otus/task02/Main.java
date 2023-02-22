package ru.otus.task02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.task02.processor.QuestionService;

@PropertySource("classpath:application.properties")
@ComponentScan
@Configuration
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Main.class);
        QuestionService process = appContext.getBean(QuestionService.class);
        process.startProcess();

    }
}
