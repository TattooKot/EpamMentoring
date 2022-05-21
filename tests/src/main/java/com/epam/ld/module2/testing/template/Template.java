package com.epam.ld.module2.testing.template;

import java.util.*;

/**
 * The type Template.
 */
public class Template {

    private final String pattern;

    public Template(String pattern) {
        pattern = pattern.replaceAll("#\\{value}", "#{tag}");
        this.pattern = pattern;
    }

    public String getResultString(List<String> tags) throws InvalidPropertiesFormatException {
        long amountOfTags = Arrays.stream(pattern.split(" "))
                .filter(s -> s.equals("#{tag}"))
                .count();

        if(Objects.isNull(tags)){
            throw new InvalidPropertiesFormatException("Tags cant be null");
        }

        if(tags.size() < amountOfTags){
            throw new InvalidPropertiesFormatException("Not enough tags added");
        }

        String result = pattern;

        for (int i = 0; i < amountOfTags; i++) {
            result = result.replace("#{tag}", tags.get(i));
        }

        return result;
    }
}
