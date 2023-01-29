package ru.otus.task01.domain;

import ru.otus.task01.io.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String TAB = "    ";
    private String questionText;
    private int validAnswerIndex;
    private List<String> answers = new ArrayList<>();

    public Question(String questionText, List<String> answers, int validAnswerIndex) {
        this.questionText = questionText;
        this.validAnswerIndex = validAnswerIndex;
        this.answers.addAll(answers);
    }

    @Override
    public String toString() {
        return this.questionText;
    }

    public void printQuestionAndAnswers() {

        int counter = 0;

        QuestionServiceImpl.printDelimiterLine();
        System.out.println(this.questionText);

        for (String answer : answers) {
            counter++;
            System.out.println(TAB + counter + ". " + answer);
        }

    }

}