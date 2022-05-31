import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
//        System.out.println(findFileWithMaxSLettersInName("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
        System.out.println(getAverageSizeOfFiles("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
    }

    public static Double getAverageSizeOfFiles(String path) {
        List<Path> pathList = getAllPaths(path);

        return pathList.stream()
                .mapToDouble(filePath -> {
                    try {
                        return Files.size(filePath);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .average()
                .orElse(Double.NaN);
    }

    public static String findFileWithMaxSLettersInName(String path) {
        List<List<Character>> filesNames = getListWithFileNames(path);
        List<Character> fileWithMaxSLetters = getFileNameWithMaxSLetters(filesNames);

        return fileWithMaxSLetters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static List<List<Character>> getListWithFileNames(String path) {
        List<Path> pathList = getAllPaths(path);

        return pathList.stream()
                .map(filePath -> filePath.getFileName().toString()
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static List<Path> getAllPaths(String path) {
        try {
            return Files.walk(Paths.get(path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private static List<Character> getFileNameWithMaxSLetters(List<List<Character>> filesList) {
        return filesList.stream()
                .max(Comparator.comparing(word -> Collections.frequency(word, 's')))
                .orElseThrow(IllegalArgumentException::new);
    }
}
