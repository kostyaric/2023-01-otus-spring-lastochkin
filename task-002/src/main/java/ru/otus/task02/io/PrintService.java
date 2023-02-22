package ru.otus.task02.io;

import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.User;

public interface PrintService {

    void printQuestion(Question question);
    void printResult(User user, int mark, boolean testPassed);

}
