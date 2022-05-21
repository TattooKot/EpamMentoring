package com.epam.ld.module2.testing.template;

import org.junit.Test;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TemplateTest {
    @Test
    public void setTemplateVariables() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value}");
        List<String> tags = new ArrayList<>();
        tags.add("tag");
        String result = template.getResultString(tags);
        assertNotNull(result);
    }

    @Test(expected = InvalidPropertiesFormatException.class)
    public void testTemplateNullValue() throws InvalidPropertiesFormatException {
        Template template = new Template("");
        template.getResultString(null);
    }



    @Test(expected = InvalidPropertiesFormatException.class)
    public void testTemplateNotEnoughTags() throws InvalidPropertiesFormatException {
        Template template = new Template(" #{value}  #{value}  #{value}  #{value}");
        List<String> tags = new ArrayList<>();
        tags.add("tag");
        tags.add("tag");
        tags.add("tag");
        template.getResultString(tags);
    }

    @Test
    public void moreThatExpectedTags() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value} #{value} #{value}");
        List<String> tags = new ArrayList<>();
        tags.add("tag");
        tags.add("tag");
        tags.add("tag");
        tags.add("tag");
        tags.add("tag");
        tags.add("tag");
        String resultString = template.getResultString(tags);
        assertNotNull(resultString);
    }
}
