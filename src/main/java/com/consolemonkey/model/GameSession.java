package com.consolemonkey.model;

import java.io.Serializable;
import java.util.List;

public class GameSession implements Serializable {

    private static final long serialVersionUID = 2905543584164919492L;
    private long sessionDuration;
    private float averageWPM;
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

    public float getAverageWPM() {
        return averageWPM;
    }

    public void setAverageWPM(float averageWPM) {
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

    public static String formatTerminalString(List<GameSession> gSessions){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n Average Speed |Duration | Accuracy "));
        gSessions.forEach(gameSession -> sb.append(String.format("\n %3.2f         | %s       | %.2f ", gameSession.getAverageWPM(), gameSession.getSessionDuration(), gameSession.getAccuracy())));

        return sb.toString();
    }
}
