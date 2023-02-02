package ru.otus.task01.dao;

import ru.otus.task01.domain.Question;
import ru.otus.task01.exceptions.ReadQuestionsException;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll() throws ReadQuestionsException;
}
