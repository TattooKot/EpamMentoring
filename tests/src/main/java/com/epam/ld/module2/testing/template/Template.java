package com.epam.ld.module2.testing.template;

import java.util.*;

/**
 * The type Template.
 */
public class Template {

    private final String pattern;

    public Template(String pattern) {
        this.pattern = pattern;
    }

    public String getResultString(List<String> tags) throws InvalidPropertiesFormatException {
        if(Objects.isNull(tags)) throw new InvalidPropertiesFormatException("Tags cant be null");

        long amountTags = Arrays.stream(pattern.split(" "))
                .filter(s -> s.equals("#{subject}"))
                .count();

        if(tags.size() < amountTags) throw new InvalidPropertiesFormatException("Not enough tags added");

        return "";
    }
}
