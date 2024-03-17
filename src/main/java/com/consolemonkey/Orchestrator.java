package com.consolemonkey;

import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;
import com.consolemonkey.gamemanager.Game;

import java.util.List;

public class Orchestrator {
    private ConsoleManager consoleManager;
    private String userName;
    private Game gameSession;

    public Orchestrator() {
        consoleManager = ConsoleManager.getInstance();
    }

    public void begin() throws Exception {
        welcome();

        // allow passing username from args to skip this step
        userName = getUserName();
        greetUser();

        // check if user config exists
        // if does, get detail model object and set to a private variable

        while (true)
            showMainMenu();
    }

    private void greetUser() {
        consoleManager.printDecoratedMessage(String.format("Welcome %s!", userName), "-", true);
    }

    private String getUserName() {
        return consoleManager.getResponse("\nEnter your name");
    }

    private void showMainMenu() throws Exception {
        var options = List.of("view stats", "new session", "quit");
        var resp = consoleManager.getResponse("\nWhat would you like to do to?'", options);

        if (resp.equalsIgnoreCase(options.get(0))) {
            viewOverallStats(null);
        } else if (resp.equalsIgnoreCase(options.get(1))) {
            startNewSession();
        } else if (resp.equalsIgnoreCase(options.get(2))) {
            System.exit(0);
        }
    }

    private void viewOverallStats(Object statsObject) {
        consoleManager.colorPrint("Your stats..", ConsoleColor.YELLOW_BOLD, true);
    }

    private void startNewSession() throws Exception {
        String text = "Hello world!";//"This is a test sentence which will later come from sentence generator.";
        gameSession = new Game(text);
        gameSession.start();
    }

    private void welcome() {
        consoleManager.printDecoratedMessage("Welcome to ConsoleMonkey!", "#", true);
    }
}
