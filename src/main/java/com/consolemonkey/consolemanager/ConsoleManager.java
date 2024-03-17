package com.consolemonkey.consolemanager;

import com.consolemonkey.consolemanager.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class ConsoleManager {
    private static Optional<ConsoleManager> instance = Optional.empty();
    private ConsoleColor defaultColor = ConsoleColor.GREEN_BOLD;
    private Terminal terminal;
    private PrintWriter writer;
    private NonBlockingReader reader;

    public ConsoleManager() {
        try {
            terminal = TerminalBuilder.builder()
                    .jna(true)
                    .system(true)
                    .build();
            writer = terminal.writer();
            reader = terminal.reader();
            // var reader2 = LineReaderBuilder.builder().build();
            // var line = reader2.readLine();
        } catch (Exception e) {
            System.out.println("Critical error occurred when creating terminal session.");
            System.exit(0);
        }
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
        writer.write(String.format("%s%s%s%s", color, text, defaultColor, addNewLine ? "\n" : ""));
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
            colorPrint(String.format("%s : ", possibleAnswers.stream().collect(Collectors.joining(" OR "))),
                    defaultColor, false);

            userAnswerRef.set(readConsoleLine());
        } while (!possibleAnswers.stream().anyMatch(a -> a.equalsIgnoreCase(userAnswerRef.get())));

        return possibleAnswers.stream().filter(a -> a.equalsIgnoreCase(userAnswerRef.get())).findFirst().get();
    }

    public String getResponse(String question) {
        String userAnswer = "";

        do {
            colorPrint(String.format("%s : ", question), ConsoleColor.GREEN, false);
            userAnswer = readConsoleLine();
        } while (userAnswer.isBlank());

        return userAnswer;
    }

    public String readConsoleLine() {
        try {
            int returnIntVal = 13;
            String output = "";
            int curInput = -1;
            do {
                curInput = reader.read();
                String curInputStr = String.valueOf((char) curInput);

                if (curInput != returnIntVal) {
                    writer.write(curInputStr);
                    output += curInputStr;
                }

            } while (curInput != returnIntVal);

            printNewLine();

            return output;
        } catch (IOException e) {
            System.out.println("error reading line");
            return "";
        }
    }

    private void printNewLine() {
        writer.write("\n");
    }

    public String readConsoleKey() {
        try {
            return String.valueOf((char) reader.read());
        } catch (IOException e) {
            System.out.println("error reading key");
            return "";
        }
    }
}
