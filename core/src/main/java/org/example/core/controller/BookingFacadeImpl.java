package org.example.core.controller;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;
import org.example.core.service.EventService;
import org.example.core.service.TicketService;
import org.example.core.service.UserService;

import java.util.List;
import java.util.Map;

public class BookingFacadeImpl implements BookingFacade{
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();
    private final TicketService ticketService = new TicketService();

    @Override
    public List<Event> getAllEvents() {
        return eventService
                .getAll()
                .values()
                .stream()
                .toList();
    }

    @Override
    public User createUser(User user) {
        return userService.save(user).getValue();
    }

    @Override
    public Ticket buyTicket(Integer userId, Integer eventId) {
        Map.Entry<Integer, User> userEntry = userService.getById(userId);
        if(userEntry.getValue() == null){
            return new Ticket();
        }

        Map.Entry<Integer, Event> eventEntry = eventService.getById(eventId);
        if(eventEntry.getValue() == null){
            return new Ticket();
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(eventEntry.getValue());
        ticket.setUser(userEntry.getValue());
        return ticket;
    }
}
