package com.consolemonkey.consolemanager;

import com.consolemonkey.consolemanager.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ConsoleManager {
    private static Optional<ConsoleManager> instance = Optional.empty();
    private ConsoleColor defaultColor = ConsoleColor.GREEN_BOLD;

    public ConsoleManager() {
    }

    private static synchronized ConsoleManager createInstance() {
        instance = Optional.of(new ConsoleManager());
        return instance.get();
    }

    public static synchronized ConsoleManager getInstance() {
        return instance.orElseGet(ConsoleManager::createInstance);
    }

    public void printDecoratedMessage(String message) {
        colorPrint(getRepeatedString("#", 40), ConsoleColor.YELLOW_BOLD, true);
        colorPrint(message, ConsoleColor.GREEN_BOLD_BRIGHT, true);
        colorPrint(getRepeatedString("#", 40), ConsoleColor.YELLOW_BOLD, true);
    }

    public void colorPrint(String text, ConsoleColor color, boolean addNewLine) {
        System.out.printf("%s%s%s%s", color, text, defaultColor, addNewLine ? "\n" : "");
    }

    public void print(String text, boolean addNewLine) {
        colorPrint(text, defaultColor, addNewLine);
    }

    private String getRepeatedString(String strToRepeat, int times) {
        return new String(new char[times]).replace("\0", strToRepeat);
    }

    public String getResponse(String question, List<String> possibleAnswers) {
        if (possibleAnswers == null || possibleAnswers.size() == 0)
            return null;

        final AtomicReference<String> userAnswerRef = new AtomicReference<>();
        do {
            colorPrint(question, ConsoleColor.GREEN_BOLD, true);
            colorPrint(String.format("%s :", possibleAnswers.stream().collect(Collectors.joining(" OR "))), defaultColor, false);

            userAnswerRef.set(readConsoleLine());
        } while (!possibleAnswers.stream().anyMatch(a -> a.equalsIgnoreCase(userAnswerRef.get())));

        return possibleAnswers.stream().filter(a -> a.equalsIgnoreCase(userAnswerRef.get())).findFirst().get();
    }


    public String getResponse(String question) {
        String userAnswer = "";

        do {
            colorPrint(String.format("%s : ", question), ConsoleColor.GREEN, false);
            userAnswer = readConsoleLine();
        }
        while (userAnswer.isBlank());

        return userAnswer;
    }

    public String readConsoleLine() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

//    public String readConsoleKey() {
//        //return System.in.read();
//    }
}

