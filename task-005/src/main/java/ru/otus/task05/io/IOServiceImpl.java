package ru.otus.task05.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream printStream;
    private final Scanner scanner;

    public IOServiceImpl (@Value("#{T(java.lang.System).out}") PrintStream printStream, @Value("#{ T(java.lang.System).in}") InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String readMessage() {
        return scanner.nextLine();
    }

    @Override
    public void printMessage(String message) {
        printStream.println(message);
    }
}
