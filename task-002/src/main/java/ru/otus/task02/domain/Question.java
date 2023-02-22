package ru.otus.task02.domain;

import java.util.ArrayList;
import java.util.List;

public class Question{

    private String questionText;
    private int validAnswerIndex;
    private List<String> answers = new ArrayList<>();

    public Question(String questionText, List<String> answers, int validAnswerIndex) {
        this.questionText = questionText;
        this.validAnswerIndex = validAnswerIndex;
        this.answers.addAll(answers);
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getValidAnswerIndex() {
        return validAnswerIndex;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return this.questionText;
    }

}
