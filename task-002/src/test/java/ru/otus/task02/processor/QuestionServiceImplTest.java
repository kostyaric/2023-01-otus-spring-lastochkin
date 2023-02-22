package ru.otus.task02.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.task02.dao.QuestionDao;
import ru.otus.task02.dao.UserDao;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.User;
import ru.otus.task02.exceptions.ReadQuestionsException;
import ru.otus.task02.io.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@DisplayName("Тестирование сервиса отвечающего за сам процесс")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private PrintService printService;
    @Mock
    private InputService inputService;
    @Mock
    private OutputService outputService;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private UserDao userDao;
    @Spy
    private List<Question> questions;
    private QuestionService questionService;

    @BeforeEach
    void init() {

        questions = new ArrayList<>();
        questions.add(new Question("What is the name of your teacher?", Arrays.asList("Aleksandr", "I don't know", "I'll ask him"), 0));
        questions.add(new Question("What kind is the surface of the moon?", Arrays.asList("hard", "soft", "Korolev said it is hard"), 2));
        questions.add(new Question("What color is the Earth sky?", Arrays.asList("green", "blue", "black", "white"), 1));
        questions.add(new Question("What profession is the best?", Arrays.asList("java developer", "blogger", "garbage collector"), 0));
        questions.add(new Question("What mark is enough to pass the task?", Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"), 5));

        Mockito.when(userDao.getUser()).thenReturn(new User("Ivanov", "Ivan"));
    }

    @Test
    @DisplayName("Проверка что тестирование не выбрасывает исключений")
    void startProcessTest() {

        questionService = new QuestionServiceImpl(printService, inputService, questionDao, userDao,3);

        Mockito.when(inputService.readMessage()).thenReturn("0");
        try {
            Mockito.when(questionDao.getAll()).thenReturn(questions);
        } catch (ReadQuestionsException e) {
            e.printStackTrace();
        }

        Assertions.assertDoesNotThrow(() -> questionService.startProcess());
    }

    @Test
    @DisplayName("Сервис возвращает корректный результат")
    void serviceReturnCorrectResult() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        outputService = new IOServiceImpl(printStream, System.in);
        printService = new PrintServiceImpl(questionDao, outputService);
        questionService = new QuestionServiceImpl(printService, inputService, questionDao, userDao,3);

        Mockito.when(inputService.readMessage()).thenReturn("1", "3", "2", "1", "6");
        try {
            Mockito.when(questionDao.getAll()).thenReturn(questions);
        } catch (ReadQuestionsException e) {
            e.printStackTrace();
        }

        questionService.startProcess();
        Assertions.assertTrue(byteArrayOutputStream.toString().endsWith("Your mark is 5\r\nYou passed the test successfully.\r\n"));
    }

}