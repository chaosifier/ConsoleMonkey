package com.consolemonkey;

import com.consolemonkey.model.*;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;

public class PlayerController {

    FileRepository fileRepository = new FileRepository();
    public Player createNewPlayer(String id) {
        Player player = new Player(id);
        fileRepository.write(player);

        return player;
    }

    public Player readPlayerData(String id) {

        System.out.println("Reading Player Data");
        Player player = fileRepository.readPlayerData(id);

        if (player.getGameSessions()==null){
            List<GameSession> gameSessions = new ArrayList<GameSession>();
            player.setGameSessions(gameSessions);
        }

        return player;
    }

    public GameSession startGameSession(Player player) {
        GameSession gameSession = new GameSession();
        gameSession.setPlayer(player);

        return gameSession;
    }

    public Player endGameSession(Player player, GameSession gameSession) {

        player.getGameSessions().add(gameSession);

        fileRepository.write(player);

        return player;
    }

    public GameSession createDummyGameSession(Player player, GameSession gameSession) {
        gameSession.setAccuracy(Math.random());
        gameSession.setSessionDuration(new Random().nextLong());
        gameSession.setAverageWPM(new Random().nextInt());
        gameSession.setSessionDuration(new Random().nextLong());

        return gameSession;
    }
}
