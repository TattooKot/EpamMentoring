package org.example.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket implements TicketInterface {
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    public Ticket(Long eventId, Long userId, Category category, Integer place) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }
}
