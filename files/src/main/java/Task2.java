import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//C:\Users\Stanislav_Kotsaha\Desktop\New folder\EpamMentoring

public class Task2 {

    public static final String RESULT_FILE = "files\\result.txt";

    public static void main(String[] args) {

        System.out.println("Select function number:");
        System.out.println("1. Find file with max 's' letter in name");
        System.out.println("2. Get average size of file");
        System.out.println("3. Get five largest files");
        System.out.println("4. Get number of files and folders by letter");

        Scanner scanner = new Scanner(System.in);
        int numOfFunction = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter path:");
        String path = scanner.nextLine();

        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            System.out.println("Invalid path");
            return;
        }

        switch (numOfFunction){
            case 1 -> findFileWithMaxSLettersInName(path);
            case 2 -> getAverageSizeOfFiles(path);
            case 3 -> getFiveLargestFiles(path);
            case 4 -> {
                System.out.println("Write letter for filter:");
                String letter = scanner.nextLine();
                getNumberOfFilesAndFoldersByLetter(path, letter);
            }
        }

        System.out.println("Result here: " + RESULT_FILE);
    }

    public static void getNumberOfFilesAndFoldersByLetter(String path, String letter){
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

        writeResultToFile("Number of files and folders: " + result);
    }

    public static void getFiveLargestFiles(String path){
        List<Path> pathList = getAllPaths(path);

        List<String> list = pathList.stream()
                .filter(Files::isRegularFile)
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
            list = list.subList(0, 5);
        }

        writeResultToFile("File largest files: " + list);
    }

    public static void getAverageSizeOfFiles(String path) {
        List<Path> pathList = getAllPaths(path);

        double result = pathList.stream()
                .mapToDouble(filePath -> {
                    try {
                        return Files.size(filePath);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .average()
                .orElse(Double.NaN);

        writeResultToFile("Average file size: " + result);
    }

    public static void findFileWithMaxSLettersInName(String path) {
        List<List<Character>> filesNames = getListWithFileNames(path);
        List<Character> fileWithMaxSLetters = getFileNameWithMaxSLetters(filesNames);

        String result = fileWithMaxSLetters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        writeResultToFile("File with max 's' letters: " + result);
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

    private static void writeResultToFile(String result){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_FILE))){
            writer.write(result);
        } catch (IOException e){
            System.out.println("Error write writing result to file");
        }
    }
}
