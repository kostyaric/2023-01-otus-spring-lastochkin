package ru.otus.task02;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.task02.io.QuestionService;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService process = appContext.getBean(QuestionService.class);
        process.startProcess();

    }
}
