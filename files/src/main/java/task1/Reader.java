package task1;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Reader {

    public static TelegramMessage readTelegramMessageFromFile(String filepath) {
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filepath))) {
            return (TelegramMessage) reader.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
