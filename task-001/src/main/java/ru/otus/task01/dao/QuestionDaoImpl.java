package ru.otus.task01.dao;

import ru.otus.task01.domain.Question;
import ru.otus.task01.exceptions.ReadQuestionsException;
import ru.otus.task01.exceptions.WronQuestionFormatException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final String DELIMITER = ",";
    private final String questionFile;

    public QuestionDaoImpl(String questionFile) {
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
    public List<Question> getAll() throws ReadQuestionsException {
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
        catch (Exception e) {
            e.printStackTrace();
            throw new ReadQuestionsException("Read question csv file error");
        }

        return questions;
    }
}
