package ru.otus.task05.io;

import org.springframework.stereotype.Service;
import ru.otus.task05.dao.QuestionDao;
import ru.otus.task05.domain.Question;
import ru.otus.task05.domain.User;

@Service
public class PrintServiceImpl implements PrintService {

    private final String TAB = "    ";
    private final QuestionDao questionDao;
    private final OutputService outputService;

    public PrintServiceImpl(QuestionDao questionDao, OutputService outputService) {
        this.questionDao = questionDao;
        this.outputService = outputService;
    }

    @Override
    public void printQuestion(Question question) {

        int counter = 0;

        printDelimiterLine();
        outputService.printMessage(question.getQuestionText());

        for (String answer : question.getAnswers()) {
            counter++;
            outputService.printMessage(TAB + counter + ". " + answer);
        }

    }

    @Override
    public void printResult(User user, int mark, boolean testPassed) {

        printDelimiterLine();
        outputService.printMessage("Your mark is " + mark);
        if (testPassed) {
            outputService.printMessage("You passed the test successfully.");
        }
        else {
            outputService.printMessage("You failed the test.");
        }

    }

    public void printDelimiterLine() {
        outputService.printMessage("----------------");
    }

}
