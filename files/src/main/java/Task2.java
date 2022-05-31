import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) throws IOException {
        System.out.println(findFileWithMaxSLettersInName("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
//
    }

    public static String findFileWithMaxSLettersInName(String path) throws IOException {
        List<List<Character>> filesNames = getListWithFileNames(path);
        List<Character> fileWithMaxSLetters = getFileNameWithMaxSLetters(filesNames);

        return fileWithMaxSLetters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static List<List<Character>> getListWithFileNames(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .map(filePath -> filePath.getFileName().toString()
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static List<Character> getFileNameWithMaxSLetters(List<List<Character>> filesList){
        return filesList.stream()
                .max(Comparator.comparing(word -> Collections.frequency(word, 's')))
                .orElseThrow(IllegalArgumentException::new);
    }
}
