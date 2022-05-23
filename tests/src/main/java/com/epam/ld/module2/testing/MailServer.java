package com.epam.ld.module2.testing;

import java.util.InvalidPropertiesFormatException;
import java.util.Objects;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) throws IllegalArgumentException {
        if(Objects.isNull(addresses) || Objects.isNull(messageContent)) {
            throw new IllegalArgumentException();
        }
    }
}
