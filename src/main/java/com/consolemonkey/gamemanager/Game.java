package com.consolemonkey.gamemanager;

import java.time.Duration;
import java.time.LocalDateTime;

import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;

public class Game {
    private ConsoleManager consoleManager;
    private String text;
    private int curIndex;
    private int mistakeCounter;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int totalWords;

    public Game(String text) throws Exception {
        if (text == null || text.isEmpty()) {
            throw new Exception("Invalid game text");
        }

        this.text = text;
        consoleManager = ConsoleManager.getInstance();
        totalWords = text.split("\\w+").length;
    }

    public void start() {
        mistakeCounter = 0;
        curIndex = 0;

        consoleManager.printDecoratedMessage("The session is ready to start. Press any key to begin!", "*", true);

        consoleManager.readConsoleKey();

        startDateTime = LocalDateTime.now();

        renderGame();

        do {
            var keyPressed = consoleManager.readConsoleKey();
            checkKeystroke(keyPressed);

            if (curIndex < text.length())
                renderGame();
        } while (curIndex < text.length());

        end();
    }

    private void checkKeystroke(char key) {
        if (text.charAt(curIndex) == key) {
            if (curIndex < text.length()) {
                curIndex++;
            }
        } else {
            mistakeCounter++;            
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    private void end() {
        endDateTime = LocalDateTime.now();
        printSessionSummary();
    }

    private void printSessionSummary() {
        var duration = Duration.between(startDateTime, endDateTime).toSeconds();
        var averageSpeed = (float) totalWords / (duration / 60f);
        var accuracy = 100 - ((float) mistakeCounter / text.length() * 100);

        consoleManager.clearTerminal();
        consoleManager.colorPrint("Session Complete!", ConsoleColor.YELLOW_BOLD, true);
        consoleManager.printDecoratedMessage(
                String.format("Average Speed: %.0f WPM\nAccuracy: %.1f%%\nDuration: %d seconds",
                        averageSpeed, accuracy, duration),
                "+", false);
    }

    private void renderGame() {
        consoleManager.clearTerminal();

        String completedSection = text.substring(0, curIndex);
        String curChar = String.valueOf(text.charAt(curIndex));
        String remainingSection = text.substring(curIndex + 1, text.length());

        consoleManager.colorPrint(completedSection, ConsoleColor.BLUE, false);
        consoleManager.colorPrint(curChar, ConsoleColor.YELLOW_UNDERLINED, false);
        consoleManager.colorPrint(remainingSection, ConsoleColor.GREEN_BOLD_BRIGHT, false);
    }
}
