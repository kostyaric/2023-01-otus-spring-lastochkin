package ru.otus.task05.dao;

import org.springframework.stereotype.Component;
import ru.otus.task05.domain.User;
import ru.otus.task05.io.IOService;
import ru.otus.task05.localisation.LocaleMessageImpl;

@Component
public class UserDaoImpl implements UserDao {

    private final IOService ioService;
    private final LocaleMessageImpl localeMessageImpl;

    public UserDaoImpl(IOService ioService, LocaleMessageImpl localeMessageImpl) {
        this.ioService = ioService;
        this.localeMessageImpl = localeMessageImpl;
    }

    @Override
    public User getUser() {
        String userFamily = "";
        String userName = "";

        ioService.printMessage(localeMessageImpl.getLoclisedMessage("user.family"));
        userFamily = ioService.readMessage();

        ioService.printMessage(localeMessageImpl.getLoclisedMessage("user.name"));
        userName = ioService.readMessage();

        return new User(userFamily, userName);
    }
}
