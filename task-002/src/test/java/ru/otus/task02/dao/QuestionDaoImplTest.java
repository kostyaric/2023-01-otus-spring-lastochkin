package ru.otus.task02.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.task02.domain.Question;
import ru.otus.task02.exceptions.ReadQuestionsException;

import java.util.List;

@DisplayName("QuestionDao - чтение данных из csv файла")
class QuestionDaoImplTest {

    private final String DELIMITER = ",";
    private QuestionDao questionDao;
    private List<Question> questions;;

    @BeforeEach
    void init() throws ReadQuestionsException {
        questionDao = new QuestionDaoImpl("questions.csv");
        questions = questionDao.getAll();
    }

    @Test
    @DisplayName("Из файла прочитано правильное количество вопросов")
    void rightQuestionNumber()  {
        Assertions.assertEquals(questions.size(), 5);
    }

    @Test
    @DisplayName("В списке вопросов корректные номера правильных ответов")
    void correctAnswerNumber() {

        for (int i = 0; i <=4; i++) {

            Question currentQuestion = questions.get(i);
            int answerInQuestion = currentQuestion.getValidAnswerIndex();

            int validAnswerNumber = -1;
            switch(i) {
                case (0):
                    validAnswerNumber = 0;
                    break;
                case (1):
                    validAnswerNumber = 2;
                    break;
                case (2):
                    validAnswerNumber = 1;
                    break;
                case (3):
                    validAnswerNumber = 0;
                    break;
                case (4):
                    validAnswerNumber = 5;
                    break;
            }
            System.out.println("Answer from question = " + answerInQuestion + "; Valid answer = " + validAnswerNumber);
            Assertions.assertEquals(answerInQuestion, validAnswerNumber);
        }

    }

    @Test
    @DisplayName("Проверка выброса исключения при чтении строки файла с неверным форматом")
    void readFileException() {
        questionDao = new QuestionDaoImpl("wrong-questions.csv");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> questionDao.getAll());
    }
}