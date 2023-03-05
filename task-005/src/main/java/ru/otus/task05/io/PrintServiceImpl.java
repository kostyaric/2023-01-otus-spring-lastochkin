package ru.otus.task05.io;

import org.springframework.stereotype.Service;
import ru.otus.task05.dao.QuestionDao;
import ru.otus.task05.domain.Question;
import ru.otus.task05.domain.User;
import ru.otus.task05.localisation.LocaleMessage;

@Service
public class PrintServiceImpl implements PrintService {

    private final String TAB = "    ";
    private final QuestionDao questionDao;
    private final OutputService outputService;
    private final LocaleMessage localeMessage;

    public PrintServiceImpl(QuestionDao questionDao, OutputService outputService, LocaleMessage localeMessage) {
        this.questionDao = questionDao;
        this.outputService = outputService;
        this.localeMessage = localeMessage;
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

        outputService.printMessage(localeMessage.getLoclisedMessage("test.mark", new String[]{String.valueOf(mark)}));
        if (testPassed) {
            outputService.printMessage(localeMessage.getLoclisedMessage("test.success"));
        }
        else {
            outputService.printMessage(localeMessage.getLoclisedMessage("test.fail"));
        }

    }

    public void printDelimiterLine() {
        outputService.printMessage("----------------");
    }

}
