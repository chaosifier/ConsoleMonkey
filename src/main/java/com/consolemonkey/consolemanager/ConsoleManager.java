package com.consolemonkey.consolemanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class ConsoleManager {
    private static Optional<ConsoleManager> instance = Optional.empty();
    public final ConsoleColor defaultColor = ConsoleColor.GREEN_BOLD_BRIGHT;
    public final ConsoleColor questionColor = ConsoleColor.CYAN_BOLD_BRIGHT;
    private Terminal terminal;
    private PrintWriter writer;
    private NonBlockingReader reader;
    private LineReader lineReader;

    public ConsoleManager() {
        try {
            terminal = TerminalBuilder.builder()
                    .name("Console Monkey")
                    .jansi(true)
                    .jna(true)
                    .system(true)
                    .dumb(false)
                    .build();
            writer = terminal.writer();
            reader = terminal.reader();
            lineReader = LineReaderBuilder.builder().build();
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

    public void printDecoratedMessage(String message, String decorator, boolean clearTerminal) {
        if (clearTerminal)
            clearTerminal();
        colorPrint(getRepeatedString(decorator, 40), ConsoleColor.YELLOW_BOLD, true);
        colorPrint(message, ConsoleColor.GREEN_BOLD_BRIGHT, true);
        colorPrint(getRepeatedString(decorator, 40), ConsoleColor.YELLOW_BOLD, true);
    }

    public void colorPrint(String text, ConsoleColor color, boolean addNewLine) {
        writer.write(String.format("%s%s%s%s", color, text, ConsoleColor.RESET, addNewLine ? "\n" : ""));
    }

    public void print(String text, boolean addNewLine) {
        colorPrint(text, defaultColor, addNewLine);
    }

    public static String getRepeatedString(String strToRepeat, int times) {
        return new String(new char[times]).replace("\0", strToRepeat);
    }

    public String getResponse(String question, List<String> possibleAnswers) {
        if (possibleAnswers == null || possibleAnswers.size() == 0)
            return null;

        final AtomicReference<String> userAnswerRef = new AtomicReference<>();
        do {
            colorPrint(question, questionColor, true);
            colorPrint(
                    String.format("%s%s : ", ConsoleColor.MAGENTA,
                            possibleAnswers.stream()
                                    .map(s -> String.format("%s%s%s", ConsoleColor.YELLOW_BOLD, s, defaultColor))
                                    .collect(Collectors.joining(" OR "))),
                    defaultColor, false);

            userAnswerRef.set(readConsoleLine());
            clearTerminal();
        } while (!possibleAnswers.stream().anyMatch(a -> a.equalsIgnoreCase(userAnswerRef.get())));

        return possibleAnswers.stream().filter(a -> a.equalsIgnoreCase(userAnswerRef.get())).findFirst().get();
    }

    public String getResponse(String question) {
        String userAnswer = "";

        do {
            colorPrint(String.format("%s : ", question), questionColor, false);
            userAnswer = readConsoleLine();
        } while (userAnswer.isBlank());

        return userAnswer;
    }

    public String readConsoleLine() {
        try {
            return lineReader.readLine();
        } catch (Exception ex) {
            return "";
        }
    }

    public void printNewLine() {
        writer.write("\n");
    }

    public char readConsoleKey() {
        try {
            return (char) reader.read();
        } catch (IOException e) {
            System.out.println("error reading key");
            return (char) 0;
        }
    }

    public void clearTerminal() {
        // terminal.puts(Capability.);
        // terminal.flush();
        writer.write("\033[H\033[2J");
        // writer.flush();
        // terminal.flush();

        // reader.callWidget(LineReader.CLEAR_SCREEN);
    }
}
