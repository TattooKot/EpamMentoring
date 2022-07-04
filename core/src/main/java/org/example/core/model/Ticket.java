package org.example.core.model;

import lombok.Data;

@Data
public class Ticket {
    private Event event;
    private User user;
}
