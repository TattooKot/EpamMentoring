import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
//        System.out.println(findFileWithMaxSLettersInName("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
//        System.out.println(getAverageSizeOfFiles("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
//        System.out.println(getFiveLargestFiles("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring"));
        System.out.println(getNumberOfFilesAndFoldersByLetter("C:\\Users\\Stanislav_Kotsaha\\Desktop\\New folder\\EpamMentoring", "s"));
    }

    public static Map<String, Long> getNumberOfFilesAndFoldersByLetter(String path, String letter){
        List<Path> allPaths = getAllPaths(path);

        long numberOfFolders = allPaths.stream()
                .filter(Files::isDirectory)
                .filter(directory -> directory.getFileName().toString().startsWith(letter))
                .count();

        long numberOfFiles = allPaths.stream()
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().startsWith(letter))
                .count();

        Map<String, Long> result = new HashMap<>();
        result.put("Folders", numberOfFolders);
        result.put("Files", numberOfFiles);

        return result;
    }

    public static List<String> getFiveLargestFiles(String path){
        List<Path> pathList = getAllPaths(path);

        List<String> list = pathList.stream()
                .sorted(Comparator.comparing((file) -> {
                    try {
                        return Files.size(file);
                    } catch (IOException e) {
                        return 0L;
                    }
                }))
                .map(filePath -> filePath.getFileName().toString())
                .collect(Collectors.toList());

        Collections.reverse(list);

        if(list.size() > 5){
            return list.subList(0, 5);
        }

        return list;
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
