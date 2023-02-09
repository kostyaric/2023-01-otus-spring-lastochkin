package ru.otus.task02.io;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class InputServiceImpl implements InputService {

    private final BufferedReader reader;

    public InputServiceImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getUserInput() {

        String userInput = "";
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInput;

    }

}
