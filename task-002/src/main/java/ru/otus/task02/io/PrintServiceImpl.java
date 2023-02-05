package ru.otus.task02.io;

import org.springframework.stereotype.Service;
import ru.otus.task02.dao.QuestionDao;
import ru.otus.task02.domain.Question;
import ru.otus.task02.exceptions.ReadQuestionsException;

@Service
public class PrintServiceImpl implements PrintService{

    private final String TAB = "    ";
    private final QuestionDao questionDao;

    public PrintServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void printQuestion(Question question) {

        int counter = 0;

        PrintServiceImpl.printDelimiterLine();
        System.out.println(question.getQuestionText());

        for (String answer : question.getAnswers()) {
            counter++;
            System.out.println(TAB + counter + ". " + answer);
        }

    }

    @Override
    public void printAllQuestions() throws ReadQuestionsException {
        questionDao.getAll().forEach(this::printQuestion);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printDelimiterLine() {
        System.out.println("----------------");
    }

}
