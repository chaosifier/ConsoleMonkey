package com.consolemonkey;

public class Main {
    public static void main(String[] args) {
        try {
            (new Orchestrator()).begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}