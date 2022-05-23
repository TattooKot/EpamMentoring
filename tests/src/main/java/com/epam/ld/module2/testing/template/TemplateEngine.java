package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Objects;

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
        return null;
    }

    private List<String> getTagsFromConsole(){
        return null;
    }
}
