package org.example.core.model;

import lombok.Data;

@Data
public class Ticket implements TicketInterface {
    private Long id;
    private Long eventId;
    private Long userId;
    private Category category;
    private Integer place;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long id) {
        eventId = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        userId = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
