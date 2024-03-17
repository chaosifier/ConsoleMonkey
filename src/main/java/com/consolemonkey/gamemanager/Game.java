package com.consolemonkey.gamemanager;

import com.consolemonkey.PlayerController;
import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;
import com.consolemonkey.model.GameSession;
import com.consolemonkey.model.Player;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game {
    private ConsoleManager consoleManager;
    private String text;
    private int curIndex;
    private int mistakeCounter;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int totalWords;

    private PlayerController pController;
    private Player player;

    public Game(String text, Player player) throws Exception {
        if (text == null || text.isEmpty()) {
            throw new Exception("Invalid game text");
        }

        this.text = text;

        consoleManager = ConsoleManager.getInstance();
        pController= new PlayerController();
        this.player= player;
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

        GameSession gSession = new GameSession();

        if (player.getBestWPM() == 0.0f){
            player.setBestWPM(averageSpeed);
            player.setWorstWPM(averageSpeed);
        }
        if (player.getBestWPM() < averageSpeed) {
            player.setBestWPM(averageSpeed);
        } else if(averageSpeed < player.getWorstWPM()){
            player.setWorstWPM(averageSpeed);
        }
        gSession.setAccuracy(accuracy);
        gSession.setAverageWPM(averageSpeed);
        gSession.setSessionDuration(duration);
        pController.endGameSession(player, gSession);

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
