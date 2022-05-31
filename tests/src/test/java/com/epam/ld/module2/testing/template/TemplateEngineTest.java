package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TemplateEngineTest {

    @Spy
    TemplateEngine templateEngine;

    @Before
    public void stUp(){
        templateEngine = spy(new TemplateEngine(true));
    }

    @Test
    public void setTemplateVariables() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value}");
        List<String> tags = List.of("tag");

        doReturn(tags).when(templateEngine).getTags();
        String result = templateEngine.generateMessage(template, new Client());

        assertNotNull(result);
    }

    @Test(expected = InvalidPropertiesFormatException.class)
    public void testTemplateNullValue() throws InvalidPropertiesFormatException {
        Template template = new Template("");
        doReturn(null).when(templateEngine).getTags();
        templateEngine.generateMessage(template, new Client());
    }

    @Test(expected = InvalidPropertiesFormatException.class)
    public void testTemplateNotEnoughTags() throws InvalidPropertiesFormatException {
        Template template = new Template(" #{value}  #{value}  #{value}  #{value}");
        List<String> tags = List.of("tag", "tag", "tag");

        doReturn(tags).when(templateEngine).getTags();

        templateEngine.generateMessage(template, new Client());
    }

    @Test
    public void moreThatExpectedTags() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value} #{value} #{value}");
        List<String> tags = List.of("tag", "tag", "tag", "tag", "tag", "tag");

        doReturn(tags).when(templateEngine).getTags();
        String result = templateEngine.generateMessage(template, new Client());

        assertNotNull(result);
    }
}
