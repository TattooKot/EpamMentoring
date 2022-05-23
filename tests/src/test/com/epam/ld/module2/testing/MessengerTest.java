package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.InvalidPropertiesFormatException;

import static org.mockito.Mockito.*;

public class MessengerTest {

    @Mock
    Client client;

    @Spy
    TemplateEngine templateEngine;

    Messenger messenger;

    @Mock
    MailServer server;

    @Before
    public void setUp(){
        client = mock(Client.class);
        server = mock(MailServer.class);
        templateEngine = spy(new TemplateEngine(true));
        messenger = new Messenger(server, templateEngine);
    }

    @Test
    public void ValidDataSendingTest() throws InvalidPropertiesFormatException {
        Template template = new Template("");
        Client client = new Client();
        client.setAddresses("");

        doReturn("Message text").when(templateEngine).generateMessage(template, client);

        String message = templateEngine.generateMessage(template, client);
        messenger.sendMessage(client, template);

        verify(server).send(client.getAddresses(), message);
    }
}