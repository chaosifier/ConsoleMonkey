package com.consolemonkey.gamemanager;

import com.consolemonkey.consolemanager.ConsoleManager;

public class Game {
    private ConsoleManager consoleManager;
    public Game(){
        consoleManager = ConsoleManager.getInstance();
    }
}
