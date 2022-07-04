package org.example.core.model;

import lombok.Data;

@Data
public class Ticket {
    private Integer id;
    private Event event;
    private User user;
}
