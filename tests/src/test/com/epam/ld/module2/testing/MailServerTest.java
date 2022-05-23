package com.epam.ld.module2.testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.InvalidPropertiesFormatException;

import static org.mockito.Mockito.*;

public class MailServerTest {

    private final String address = "addresses";
    private final String message = "message";

    @Mock
    MailServer server;

    @Before
    public void setUp(){
        server = mock(MailServer.class);
    }

    @Test
    public void validInfoTest() {
        server.send(address, message);
        verify(server).send(address, message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAddressTest() {
        doThrow(new IllegalArgumentException()).when(server).send(null, message);
        server.send(null, message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullMessageTest() {
        doThrow(new IllegalArgumentException()).when(server).send(address, null);
        server.send(address, null);
    }
}
