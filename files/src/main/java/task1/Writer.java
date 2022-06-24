package task1;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Writer {

    public static void writeTelegramMessageToFile(TelegramMessage message, String filepath) {
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filepath))) {
            writer.writeObject(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
