package com.consolemonkey;

import com.consolemonkey.consolemanager.ConsoleColor;
import com.consolemonkey.consolemanager.ConsoleManager;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import java.util.List;

public class Orchestrator {
    private ConsoleManager consoleManager;
    private String userName;

    public Orchestrator() {
        consoleManager = ConsoleManager.getInstance();
    }

    public void begin() {
      try{
          Terminal terminal = TerminalBuilder.builder()
                  .jna(true)
                  .system(true)
                  .build();

// raw mode means we get keypresses rather than line buffered input
          terminal.enterRawMode();
          var reader = terminal .reader();

          int read = reader.read();

          reader.close();
          terminal.close();
      }
      catch (Exception ex){
          System.out.println(ex);
      }


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
        consoleManager.colorPrint(String.format("Welcome %s!", userName), ConsoleColor.GREEN, true);
    }

    private String getUserName() {
        return consoleManager.getResponse("\nEnter your name");
    }

    private void showMainMenu() {
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

    private void startNewSession() {
        consoleManager.colorPrint("New session..", ConsoleColor.YELLOW_BOLD, true);
    }

    private void welcome() {
        consoleManager.printDecoratedMessage("Welcome to ConsoleMonkey!");
    }
}
