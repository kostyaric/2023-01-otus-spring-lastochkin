package ru.otus.task01.io;

import ru.otus.task01.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QuestionService {

    private static final String DELIMITER = ",";
    private static final String RESOURCE_NAME = "questions.csv";

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

    private Set<Question> readQuestions() {

        Set<Question> questions = new LinkedHashSet<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RESOURCE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] questionAndAnswers = line.split(DELIMITER);
                if (questionAndAnswers.length < 3) {
                    System.out.println("!!!ERROR. String '" + line + "' have to contains 3 parameters as minimum");
                    continue;
                }
                questions.add(createQuestion(questionAndAnswers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static void printDelimiterLine() {
        System.out.println("----------------");
    }

    public void printQuestions() {
        printDelimiterLine();
        for (Question question : readQuestions()) {
            System.out.println(question);
//            question.printQuestionAndAnswers();
        }
    }

}
