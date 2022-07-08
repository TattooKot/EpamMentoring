package org.example.core.controller;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;
import org.example.core.service.EventService;
import org.example.core.service.TicketService;
import org.example.core.service.UserService;

import java.util.Date;
import java.util.List;

public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService = new EventService();
    private final UserService userService = new UserService();
    private final TicketService ticketService = new TicketService();

    @Override
    public Event getEventById(long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getForDate(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteById(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteById(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new Ticket(eventId, userId, category, place);
        return ticketService.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getUserTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
