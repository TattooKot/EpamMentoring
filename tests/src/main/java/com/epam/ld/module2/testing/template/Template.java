package com.epam.ld.module2.testing.template;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Objects;

/**
 * The type Template.
 */
public class Template {

    public String getResultString(List<String> tags) throws InvalidPropertiesFormatException {
        if(Objects.isNull(tags)) throw new InvalidPropertiesFormatException("Tags cant be null");

        return "";
    }
}
