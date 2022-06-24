package task1;

public class Main {

    private static final String filepath = "files/src/main/java/task1/file.txt";

    public static void main(String[] args) {
        String[] emoji = {":)", ":("};
        TelegramMessage message = new TelegramMessage("Stas", "Taras", "Hello, mentor! :)", emoji);
        Writer.writeTelegramMessageToFile(message, filepath);
        System.out.println(Reader.readTelegramMessageFromFile(filepath));
    }
}
