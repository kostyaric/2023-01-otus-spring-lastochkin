package ru.otus.task01.io;

import ru.otus.task01.domain.Printable;

public class PrintServiceImpl implements PrintService{
    @Override
    public void printItem(Printable item) {
        item.selfPrint();
    }

    public static void printDelimiterLine() {
        System.out.println("----------------");
    }

}
