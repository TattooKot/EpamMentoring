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
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TemplateEngineTest {

    @Spy
    TemplateEngine templateEngine;

    Client client;

    @Before
    public void stUp(){
        templateEngine = spy(new TemplateEngine(true));
        client = new Client();
    }

    @Test
    public void setTemplateVariables() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value}");
        List<String> tags = List.of("tag");

        doReturn(tags).when(templateEngine).getTags();
        String result = templateEngine.generateMessage(template, client);

        assertNotNull(result);
    }

    @Test
    public void testTemplateNullValue() {
        Template template = new Template("");
        doReturn(null).when(templateEngine).getTags();
        assertThrows(InvalidPropertiesFormatException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void testTemplateNotEnoughTags() {
        Template template = new Template(" #{value}  #{value}  #{value}  #{value}");
        List<String> tags = List.of("tag", "tag", "tag");

        doReturn(tags).when(templateEngine).getTags();
        assertThrows(InvalidPropertiesFormatException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void moreThatExpectedTags() throws InvalidPropertiesFormatException {
        Template template = new Template("#{value} #{value} #{value}");
        List<String> tags = List.of("tag", "tag", "tag", "tag", "tag", "tag");

        doReturn(tags).when(templateEngine).getTags();
        String result = templateEngine.generateMessage(template, client);

        assertNotNull(result);
    }
}
