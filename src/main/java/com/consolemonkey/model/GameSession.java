package com.consolemonkey.model;

import java.io.Serializable;

public class GameSession implements Serializable {

    private static final long serialVersionUID = 2905543584164919492L;
    private long sessionDuration;
    private int averageWPM;
    private double accuracy;
    private Player player;
    private WordPool wordPool;

    private Boolean isPrivate;

    public GameSession() {
    }

    public long getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(long sessionDuration) {
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

    @Override
    public String toString() {
        return STR."GameSession{sessionDuration=\{sessionDuration}, averageWPM=\{averageWPM}, accuracy=\{accuracy}, player=\{player}, wordPool=\{wordPool}, isPrivate=\{isPrivate}\{'}'}";
    }
}
