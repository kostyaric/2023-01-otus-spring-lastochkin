package ru.otus.task01.io;

import ru.otus.task01.dao.QuestionDao;
import ru.otus.task01.domain.Question;
import ru.otus.task01.exceptions.ReadQuestionsException;

public class PrintServiceImpl implements PrintService{

    private final String TAB = "    ";
    private final QuestionDao questionDao;

    public PrintServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void printQuesion(Question question) {

        int counter = 0;

        PrintServiceImpl.printDelimiterLine();
        System.out.println(question.getQuestionText());

        for (String answer : question.getAnswers()) {
            counter++;
            System.out.println(TAB + counter + ". " + answer);
        }

    }

    @Override
    public void print() throws ReadQuestionsException {
        questionDao.getAll().forEach(this::printQuesion);
    }

    public static void printDelimiterLine() {
        System.out.println("----------------");
    }

}
