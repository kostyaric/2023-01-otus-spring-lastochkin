package ru.otus.task02.io;

import ru.otus.task02.exceptions.ReadQuestionsException;

public class QuestionServiceImpl implements QuestionService {

    private final PrintService printService;

    public QuestionServiceImpl(PrintService printService) {
        this.printService = printService;
    }

    public void startProcess() {
        try {
            printService.print();
        } catch (ReadQuestionsException e) {
            e.printStackTrace();
        }
    }

}
