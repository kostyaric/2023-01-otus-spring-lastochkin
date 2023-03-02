package ru.otus.task05.dao;

import ru.otus.task05.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
