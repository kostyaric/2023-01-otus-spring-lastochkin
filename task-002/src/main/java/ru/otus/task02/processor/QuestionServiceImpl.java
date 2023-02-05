package ru.otus.task02.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.task02.dao.QuestionDao;
import ru.otus.task02.domain.Question;
import ru.otus.task02.exceptions.ReadQuestionsException;
import ru.otus.task02.io.InputService;
import ru.otus.task02.io.PrintService;
import ru.otus.task02.io.PrintServiceImpl;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final PrintService printService;
    private final InputService inputService;
    private final QuestionDao questionDao;
    private final int minimumCorrectAnswers;

    public QuestionServiceImpl(PrintService printService,
                               InputService inputService,
                               QuestionDao questionDao,
                               @Value("${minimum.correct.ansewrs}") int minimumCorrectAnswers) {

        this.printService = printService;
        this.inputService = inputService;
        this.questionDao = questionDao;
        this.minimumCorrectAnswers = minimumCorrectAnswers;
    }

    private int checkAnswer(Question question, String answer) {
        int validAnswerNumber = question.getValidAnswerIndex() + 1;
        int userAnswerNumber = Integer.parseInt(answer);

        return userAnswerNumber == validAnswerNumber ? 1 : 0;
    }

    public void startProcess() {

        int validAnswerCount = 0;

        try {
            List<Question> allQuestions = questionDao.getAll();
            for (Question question : allQuestions) {
                printService.printQuestion(question);
                String userAnswer = inputService.getUserInput();
                validAnswerCount += checkAnswer(question, userAnswer);
            }
        } catch (ReadQuestionsException e) {
            e.printStackTrace();
        }

        PrintServiceImpl.printMessage("Correct answers is " + validAnswerCount);
        if (validAnswerCount >= minimumCorrectAnswers) {
            PrintServiceImpl.printMessage("You passed the test successfully. Your mark is " + validAnswerCount);
        }
        else {
            PrintServiceImpl.printMessage("You failed the test.");
        }
    }

}
