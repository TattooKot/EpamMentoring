package com.epam.ld.module2.testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.InvalidPropertiesFormatException;

import static org.junit.Assert.assertThrows;
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

    @Test
    public void nullAddressTest() {
        doThrow(new IllegalArgumentException()).when(server).send(null, message);
        assertThrows(IllegalArgumentException.class, () -> server.send(null, message));
    }

    @Test
    public void nullMessageTest() {
        doThrow(new IllegalArgumentException()).when(server).send(address, null);
        assertThrows(IllegalArgumentException.class, () -> server.send(address, null));
    }
}
