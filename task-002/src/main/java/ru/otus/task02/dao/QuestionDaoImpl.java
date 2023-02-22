package ru.otus.task02.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.task02.domain.Question;
import ru.otus.task02.exceptions.ReadQuestionsException;
import ru.otus.task02.exceptions.WronQuestionFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private static final String DELIMITER = ",";
    private final String questionFile;

    public QuestionDaoImpl(@Value("${question.file}") String questionFile) {
        this.questionFile = questionFile;
    }

    private Question createQuestion(String[] questionAndAnswers) {

        int validIndex = 0;
        String questionText = questionAndAnswers[0];
        List<String> answers = new ArrayList<>();

        for (int i = 1; i < questionAndAnswers.length - 1; i += 2) {
            answers.add(questionAndAnswers[i].trim());
            if (questionAndAnswers[i +1].trim().equals("true")) {
                validIndex = answers.size() - 1;
            }
        }

        return new Question(questionText, answers, validIndex);

    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionFile);)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] questionAndAnswers = line.split(DELIMITER);
                if (questionAndAnswers.length < 3) {
                    throw new WronQuestionFormatException("String '" + line + "' have to contains 3 parameters as minimum");
                }
                questions.add(createQuestion(questionAndAnswers));
            }
        }
        catch (IOException e) {
            throw new ReadQuestionsException("Read question csv file error", e);
        }

        return questions;
    }
}
