package com.consolemonkey.model;

import java.io.Serializable;
import java.util.List;

import com.consolemonkey.consolemanager.ConsoleManager;

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
        return String.format("GameSession{sessionDuration=%n, averageWPM=%f, accuracy=%n, player=%s, wordPool=%s, isPrivate=%s}}", sessionDuration, averageWPM, accuracy, player, wordPool, isPrivate);
    }

    public static String formatTerminalString(List<GameSession> gSessions){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < gSessions.size(); i++){
            var sess = gSessions.get(i);

            sb.append(String.format("Average Speed: %.0f WPM\n", sess.getAverageWPM()));
            sb.append(String.format("Accuracy: %.2f%%\n", sess.getAccuracy()));
            sb.append(String.format("Duration: %d secs", sess.getSessionDuration()));

            if(i < gSessions.size() - 1){
                sb.append(String.format("\n%s\n", ConsoleManager.getRepeatedString("-", 20)));
            }
        }
        
        return sb.toString();
    }
}
