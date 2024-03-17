package model;

import java.io.*;
import java.util.logging.Logger;

public class FileRepository {
    private static final Logger LOG = Logger.getLogger(FileRepository.class.getName());

    public FileRepository() {
    }

    public void write(Player player) {

        File file = new File(player.getId());
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player);
            oos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAllFiles() {

        //File file = new File(player.getId());
        //Player p = (Player) readObjectFromFile(file);
    }

    public Player readPlayerData(String id) {
        Player loginUser = new Player(id);
        File file = new File(loginUser.getId());
        return (Player) readObjectFromFile(file);
    }

    public static Object readObjectFromFile(File file) {
        Object result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
