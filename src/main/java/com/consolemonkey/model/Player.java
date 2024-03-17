package com.consolemonkey.model;

import java.io.Serializable;
import java.util.List;

public class Player extends User implements Serializable {

    private static final long serialVersionUID = -153110093322334742L;
    private int bestWPM;
    private int worstWPM;
    private List<GameSession> gameSessions;

    public Player(String id) {
        super(id);
    }

    public int getBestWPM() {
        return bestWPM;
    }

    public void setBestWPM(int bestWPM) {
        this.bestWPM = bestWPM;
    }

    public int getWorstWPM() {
        return worstWPM;
    }

    public void setWorstWPM(int worstWPM) {
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
}
