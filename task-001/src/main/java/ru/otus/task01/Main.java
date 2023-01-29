package ru.otus.task01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.task01.io.QuestionServiceImpl;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionServiceImpl reader = appContext.getBean(QuestionServiceImpl.class);
        reader.printQuestions();

    }
}
