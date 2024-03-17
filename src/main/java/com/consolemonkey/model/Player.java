package com.consolemonkey.model;

import java.io.Serializable;
import java.util.List;

public class Player extends User implements Serializable {

    private static final long serialVersionUID = -153110093322334742L;
    private float bestWPM;
    private float worstWPM;
    private List<GameSession> gameSessions;

    public Player(String id) {
        super(id);
    }

    public float getBestWPM() {
        return bestWPM;
    }

    public void setBestWPM(float bestWPM) {
        this.bestWPM = bestWPM;
    }

    public float getWorstWPM() {
        return worstWPM;
    }

    public void setWorstWPM(float worstWPM) {
        this.worstWPM = worstWPM;
    }

    public List<GameSession> getGameSessions() {
        return gameSessions;
    }

    public void setGameSessions(List<GameSession> gameSessions) {
        this.gameSessions = gameSessions;
    }

    @Override
    public String toString() {
        //", gameSessions=" + gameSessions +
        return STR."Player{id= \{getId()}, bestWPM=\{bestWPM}, worstWPM=\{worstWPM}\{'}'}";
    }

    public static String formatTerminalString(Player player){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n ID: %s \n Best WPM: %s \n Worst WPM: %s ", player.getId() ,player.getBestWPM(), player.getWorstWPM()));

        return sb.toString();
    }
}
