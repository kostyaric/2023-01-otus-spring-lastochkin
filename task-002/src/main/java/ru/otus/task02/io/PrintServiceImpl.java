package ru.otus.task02.io;

import org.springframework.stereotype.Service;
import ru.otus.task02.dao.QuestionDao;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.User;
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

        printDelimiterLine();
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

    public void printAnyMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printResult(User user, int mark, boolean testPassed) {

        printDelimiterLine();
        printAnyMessage("Your mark is " + mark);
        if (testPassed) {
            printAnyMessage("You passed the test successfully.");
        }
        else {
            printAnyMessage("You failed the test.");
        }

    }

    public static void printDelimiterLine() {
        System.out.println("----------------");
    }

}
