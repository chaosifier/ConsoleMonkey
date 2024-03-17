import model.FileRepository;
import model.GameSession;
import model.Player;

import java.util.List;
import java.util.Random;

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

        List<GameSession> gameSessions = player.getGameSessions();

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
