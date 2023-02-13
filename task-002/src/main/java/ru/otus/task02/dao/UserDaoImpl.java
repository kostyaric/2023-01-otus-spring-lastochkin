package ru.otus.task02.dao;

import org.springframework.stereotype.Component;
import ru.otus.task02.domain.User;
import ru.otus.task02.io.InputService;
import ru.otus.task02.io.PrintService;

@Component
public class UserDaoImpl implements UserDao {

    private final PrintService printService;
    private final InputService inputService;

    public UserDaoImpl(PrintService printService, InputService inputService) {
        this.printService = printService;
        this.inputService = inputService;
    }

    @Override
    public User getUser() {
        String userFamily = "";
        String userName = "";

        printService.printAnyMessage("Input your family:");
        userFamily = inputService.getUserInput();

        printService.printAnyMessage("Input your name:");
        userName = inputService.getUserInput();

        return new User(userFamily, userName);
    }
}
