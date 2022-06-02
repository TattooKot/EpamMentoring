import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task3 {
//    10 mb: 1.~36000; 2.11ms;
//    100 mb: 1.~427779; 2.23ms;
//    1 gb: 1. ; 2.108;

    public static final String FILE_FROM = "files\\1gbFile.txt";
    public static final String DESTINATION_FOLDER = "files\\resultFolder";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        copyWithBuffer(Paths.get(FILE_FROM), Paths.get(DESTINATION_FOLDER));
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
//        writeToFile(Paths.get(FILE_FROM), Paths.get(DESTINATION_FOLDER));
    }

    public static void copyOnlyStream(Path from, Path destinationFolder) {
        String resultFile = getValidInfo(from, destinationFolder);
        if (resultFile == null) return;

        try (FileInputStream in = new FileInputStream(from.toFile());
             FileOutputStream out = new FileOutputStream(resultFile)) {

            int i;

            while ((i = in.read()) != -1) {
                out.write(i);
            }

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyWithBuffer(Path from, Path destinationFolder) {
        String resultFile = getValidInfo(from, destinationFolder);
        if (resultFile == null) return;


        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(from.toFile()), 100);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(resultFile))) {

            while (in.available() < 0) {
                byte[] bytes = in.readNBytes(100);
                out.write(bytes);
            }

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValidInfo(Path from, Path destinationFolder) {
        if (!Files.exists(from)) {
            System.out.println("File doesn`t exist");
            return null;
        }

        if (!Files.isDirectory(destinationFolder)) {
            System.out.println("Only folders applies");
            return null;
        }

        return destinationFolder + "\\" + from.getFileName().toString();
    }

}
