package ru.otus.task05.io;

import ru.otus.task05.domain.Question;
import ru.otus.task05.domain.User;

public interface PrintService {

    void printQuestion(Question question);
    void printResult(User user, int mark, boolean testPassed);

}
