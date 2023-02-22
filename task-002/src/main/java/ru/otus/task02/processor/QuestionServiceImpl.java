package ru.otus.task02.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.task02.dao.QuestionDao;
import ru.otus.task02.dao.UserDao;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.User;
import ru.otus.task02.io.InputService;
import ru.otus.task02.io.PrintService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final PrintService printService;
    private final InputService inputService;
    private final QuestionDao questionDao;
    private final UserDao userDao;
    private final int minimumCorrectAnswers;

    public QuestionServiceImpl(PrintService printService,
                               InputService inputService,
                               QuestionDao questionDao,
                               UserDao userDao,
                               @Value("${minimum.correct.ansewrs}") int minimumCorrectAnswers) {

        this.printService = printService;
        this.inputService = inputService;
        this.questionDao = questionDao;
        this.userDao = userDao;
        this.minimumCorrectAnswers = minimumCorrectAnswers;
    }

    private boolean checkAnswer(Question question, String answer) {
        int validAnswerNumber = question.getValidAnswerIndex() + 1;
        int userAnswerNumber = Integer.parseInt(answer);

        return userAnswerNumber == validAnswerNumber;
    }

    public void startProcess() {

        int validAnswerCount = 0;
        User user = userDao.getUser();

        List<Question> allQuestions = questionDao.getAll();
        for (Question question : allQuestions) {
            printService.printQuestion(question);
            String userAnswer = inputService.readMessage();
            validAnswerCount += checkAnswer(question, userAnswer) ? 1 : 0;
        }

        printService.printResult(user, validAnswerCount, validAnswerCount >= minimumCorrectAnswers);

    }

}
