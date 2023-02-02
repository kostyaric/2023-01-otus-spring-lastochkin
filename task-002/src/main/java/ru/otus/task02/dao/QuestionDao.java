package ru.otus.task02.dao;

import ru.otus.task02.domain.Question;
import ru.otus.task02.exceptions.ReadQuestionsException;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll() throws ReadQuestionsException;
}
