package by.valvik.mazerunner.view;

import java.util.Scanner;

public class Console {

    private static final Console INSTANCE = new Console();

    private final Scanner scanner;

    private Console() {

         scanner = new Scanner(System.in);

    }

    public String getCommandLine(String requestLine) {

        System.out.print(requestLine);

        return scanner.nextLine();

    }

    public static Console getInstance() {

        return INSTANCE;

    }

}
