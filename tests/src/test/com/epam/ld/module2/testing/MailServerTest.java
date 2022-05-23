package com.epam.ld.module2.testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.InvalidPropertiesFormatException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    public void validInfoTest() throws InvalidPropertiesFormatException {
        server.send(address, message);
        verify(server).send(address, message);
    }

    @Test(expected = InvalidPropertiesFormatException.class)
    public void nullAddressTest() throws InvalidPropertiesFormatException {
        fail();
    }

    @Test(expected = InvalidPropertiesFormatException.class)
    public void nullMessageTest() throws InvalidPropertiesFormatException {
        fail();
    }
}
