package ru.otus.task05.dao;

import org.springframework.stereotype.Component;
import ru.otus.task05.domain.User;
import ru.otus.task05.io.IOService;

@Component
public class UserDaoImpl implements UserDao {

    private final IOService ioService;

    public UserDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public User getUser() {
        String userFamily = "";
        String userName = "";

        ioService.printMessage("Input your family:");
        userFamily = ioService.readMessage();

        ioService.printMessage("Input your name:");
        userName = ioService.readMessage();

        return new User(userFamily, userName);
    }
}
