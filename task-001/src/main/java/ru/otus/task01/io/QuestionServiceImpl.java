package ru.otus.task01.io;

import ru.otus.task01.dao.QuestionDao;
import ru.otus.task01.domain.Question;
import ru.otus.task01.io.exceptions.ReadQuestionsException;
import ru.otus.task01.io.exceptions.WronQuestionFormatException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QuestionServiceImpl implements QuestionService {

    private static final String DELIMITER = ",";
    private final PrintService printService;
    private final String questionFile;
    private final QuestionDao questionDao;

    public QuestionServiceImpl(PrintService printService, String questionFile, QuestionDao questionDao) {
        this.printService = printService;
        this.questionFile = questionFile;
        this.questionDao = questionDao;
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

    private Set<Question> readQuestions() throws ReadQuestionsException {

        Set<Question> questions = new LinkedHashSet<>();
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

    public void startProcess() {
        try {
            readQuestions().stream().forEach(printService::printItem);
        } catch (ReadQuestionsException e) {
            e.printStackTrace();
        }
    }

}
