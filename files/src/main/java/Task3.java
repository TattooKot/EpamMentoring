import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task3 {
//    10 mb:  1:~36000ms;  2:11ms;  3:222ms;    4:162ms;
//    100 mb: 1:~427779ms; 2:23ms;  3:~1630ms;  4:775ms;
//    1 gb:   1. ;         2.108ms; 3:~17831ms; 4:4722ms;

    public static final String FILE_FROM = "files\\10mbFile.txt";
    public static final String DESTINATION_FOLDER = "files\\resultFolder";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        channelCopy(Paths.get(FILE_FROM), Paths.get(DESTINATION_FOLDER));
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
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

    public static void channelCopy(Path from, Path destinationFolder) {
        String resultFile = getValidInfo(from, destinationFolder);
        if (resultFile == null) return;

        try (FileInputStream fin = new FileInputStream(from.toFile());
             FileOutputStream fos = new FileOutputStream(resultFile);
             FileChannel in = fin.getChannel();
             FileChannel out = fos.getChannel()) {

            ByteBuffer buf = ByteBuffer.allocateDirect(100);
            while (in.read(buf) > 0) {
                out.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioCopy(Path from, Path destinationFolder) {
        String resultFile = getValidInfo(from, destinationFolder);
        if (resultFile == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            List<String> collect = Files.lines(from).toList();
            writer.write(collect.toString());
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
