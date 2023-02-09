package ru.otus.task02.io;

import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.User;
import ru.otus.task02.exceptions.ReadQuestionsException;

public interface PrintService {

    void printQuestion(Question question);
    void printAllQuestions() throws ReadQuestionsException;
    void printAnyMessage(String message);
    void printResult(User user, int mark, boolean testPassed);

}
