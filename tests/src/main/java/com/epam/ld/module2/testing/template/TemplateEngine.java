package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

public interface TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */

    String generateMessage(Template template, Client client);
}
