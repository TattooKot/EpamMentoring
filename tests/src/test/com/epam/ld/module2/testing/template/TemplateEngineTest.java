package com.epam.ld.module2.testing.template;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TemplateEngineTest {

    @Test
    public void setTemplateVariables(){
        Template template = new Template();
        List<String> tags = new ArrayList<>();
        String result = template.getResultString(tags);
        assertNotNull(result);
    }

    @Test(expected = ClassFormatError.class)
    public void testTemplateNullValue(){
        fail();
    }

}
