import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task3 {

    public static final String FILE_FROM = "files\\result.txt";
    public static final String DESTINATION_FOLDER = "files\\resultFolder";

    public static void main(String[] args) {

        copyOnlyStream(Paths.get(FILE_FROM), Paths.get(DESTINATION_FOLDER));
    }

    public static void copyOnlyStream(Path from, Path destinationFolder){
        if(!Files.exists(from)) {
            System.out.println("File doesn`t exist");
            return;
        }

        if(!Files.isDirectory(destinationFolder)){
            System.out.println("Only folders applies");
            return;
        }

        String resultFile = destinationFolder + "\\" + from.getFileName().toString();

        try(FileInputStream in = new FileInputStream(from.toFile());
            FileOutputStream out = new FileOutputStream(resultFile)){

            int i;

            while ((i = in.read()) != -1){
                out.write(i);
            }

            System.out.println("Done");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
