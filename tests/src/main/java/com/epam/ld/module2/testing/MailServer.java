package com.epam.ld.module2.testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

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

        /* Change output here */
//        sendToConsole(messageContent, addresses);
        sendToFile(messageContent, addresses);
    }

    private void sendToConsole(String message, String addresses){
        System.out.printf("Message: %s\nClient(s): %s", message, addresses);
    }
    private void sendToFile(String message, String addresses){
        System.out.println("Enter path to output file: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File yourFile = new File(filePath);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            yourFile.createNewFile();
            String result = String.format("Message: %s\nClient(s): %s", message, addresses);
            writer.write(result);
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }
}
