package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

/**
 * If you wanna run application in console mode:
 * create @templateEngine with FALSE parameter
 * uncomment line in @MailServer.send method
 *
 * If you wanna run application in file mode:
 * create @templateEngine with TRUE parameter
 * uncomment line in @MailServer.send method
 *
 * Template example:
 *      Hello, #{value}
 *
 * File path example:
 *      tests/src/main/resources/inputFile.txt
 *  */

public class Application {

    static Scanner scanner = new Scanner(System.in);
    static MailServer server = new MailServer();
    static TemplateEngine templateEngine = new TemplateEngine(true);
    static Messenger messenger = new Messenger(server, templateEngine);

    public static void main(String[] args) throws InvalidPropertiesFormatException {
        Client client = new Client();
        client.setAddresses("kotsagastas@gmail.com");

        System.out.println("Write template for message:");
        String stringTemplate = scanner.nextLine();

        Template template = new Template(stringTemplate);
        messenger.sendMessage(client, template);
    }
}
