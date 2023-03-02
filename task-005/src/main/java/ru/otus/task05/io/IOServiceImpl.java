package ru.otus.task05.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream printStream;
    private final BufferedReader reader;

    public IOServiceImpl (@Value("#{T(java.lang.System).out}") PrintStream printStream, @Value("#{ T(java.lang.System).in}") InputStream inputStream) {
        this.printStream = printStream;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public String readMessage() {
        String userInput = "";
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("");
        }
        return userInput;
    }

    @Override
    public void printMessage(String message) {
        printStream.println(message);
    }
}
