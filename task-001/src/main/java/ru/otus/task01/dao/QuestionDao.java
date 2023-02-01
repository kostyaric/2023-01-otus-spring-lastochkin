package ru.otus.task01.dao;

import ru.otus.task01.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
