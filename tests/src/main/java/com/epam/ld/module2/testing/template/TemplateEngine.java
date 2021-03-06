package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    private final boolean FROM_FILE;

    public TemplateEngine(boolean FROM_FILE) {
        this.FROM_FILE = FROM_FILE;
    }

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */

    public String generateMessage(Template template, Client client) throws InvalidPropertiesFormatException {
        String result = template.getPattern();

        long amountOfTags = Arrays.stream(result.split(" "))
                .filter(s -> s.equals("#{tag}"))
                .count();

        List<String> tags = getTags();

        if(Objects.isNull(tags)) throw new InvalidPropertiesFormatException("Tags cant be null");

        if(tags.size() < amountOfTags) throw new InvalidPropertiesFormatException("Not enough tags added");

        for (int i = 0; i < amountOfTags; i++) {
            result = result.replace("#{tag}", tags.get(i));
        }

        return result;
    }

    public List<String> getTags(){
        return FROM_FILE ? getTagsFromFile() : getTagsFromConsole();
    }

    private List<String> getTagsFromFile(){
        System.out.println("Enter path to file with tags:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        try {
            return Files.lines(Paths.get(filePath))
                    .filter(line -> !line.equals("q"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("IO Error");
            return new ArrayList<>();
        }
    }

    private List<String> getTagsFromConsole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write tags witch you wanna add, and press Enter");
        System.out.println("(write q + enter to quit)");
        List<String> tags = new ArrayList<>();
        String line;

        while (!(line = scanner.nextLine()).equals("q")){
            tags.add(line);
        }
        return tags;
    }
}
