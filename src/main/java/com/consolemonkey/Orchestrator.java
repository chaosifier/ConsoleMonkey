package com.consolemonkey;

import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;
import com.consolemonkey.gamemanager.Game;
import com.consolemonkey.model.GameSession;
import com.consolemonkey.model.Player;

import java.util.List;

public class Orchestrator {
    private ConsoleManager consoleManager;
    private String userName;
    private Game gameSession;
    private PlayerController pController;
    private Player player;

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
        pController = new PlayerController();
        player = pController.readPlayerData(userName);

        while (true)
            showMainMenu(player);
    }

    private void greetUser() {
        consoleManager.printDecoratedMessage(String.format("Welcome %s!", userName), "-", true);
    }

    private String getUserName() {
        return consoleManager.getResponse("\nEnter your name");
    }

    private void showMainMenu(Player player) throws Exception {
        var options = List.of("view stats", "new session", "quit");
        var resp = consoleManager.getResponse("\nWhat would you like to do to?'", options);

        if (resp.equalsIgnoreCase(options.get(0))) {
            viewOverallStats(player);
        } else if (resp.equalsIgnoreCase(options.get(1))) {
            startNewSession(player);
        } else if (resp.equalsIgnoreCase(options.get(2))) {
            System.exit(0);
        }
    }

    private void viewOverallStats(Player player) {
        consoleManager.colorPrint("Your stats..", ConsoleColor.YELLOW_BOLD, true);
        consoleManager.printDecoratedMessage(String.format("Your stats %s \nSession : %s",Player.formatTerminalString(player), GameSession.formatTerminalString(player.getGameSessions())), "-", true);
        
    }

    private void startNewSession(Player player) throws Exception {
        String text = "Hello world!";//"This is a test sentence which will later come from sentence generator.";
        gameSession = new Game(text, player);
        gameSession.start();
    }

    private void welcome() {
        consoleManager.printDecoratedMessage("Welcome to ConsoleMonkey!", "#", true);
    }
}
