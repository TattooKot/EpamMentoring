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

    public String getPattern() {
        return pattern;
    }
}
