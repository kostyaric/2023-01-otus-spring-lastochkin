package ru.otus.task01.io;

import ru.otus.task01.exceptions.ReadQuestionsException;

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
