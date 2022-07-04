package org.example.core.controller;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;

import java.util.List;

public interface BookingFacade {
    List<Event> getAllEvents();
    User createUser(User user);
    Ticket buyTicket(User user, Event event);
}
