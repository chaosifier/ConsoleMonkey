package com.consolemonkey.model;

import java.util.List;

public class Player extends User{
    private int bestWPM;
    private int worstWPM;
    private List<GameSession> sessions;

    public Player() {
        super();
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

    public List<GameSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession> sessions) {
        this.sessions = sessions;
    }
}
