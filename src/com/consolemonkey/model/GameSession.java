package com.consolemonkey.model;

import java.time.Period;

public class GameSession {
    private Period sessionDuration;
    private int averageWPM;
    private double accuracy;
    private Player player;
    private WordPool wordPool;

    private Boolean isPrivate;

    public GameSession() {
    }

    public Period getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(Period sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public int getAverageWPM() {
        return averageWPM;
    }

    public void setAverageWPM(int averageWPM) {
        this.averageWPM = averageWPM;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public WordPool getWordPool() {
        return wordPool;
    }

    public void setWordPool(WordPool wordPool) {
        this.wordPool = wordPool;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
