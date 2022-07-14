package org.example.core.service;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;
import org.example.core.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket).orElseGet(Ticket::new);
    }

    public Ticket getById(Long id) {
        return ticketRepo.getById(id).orElseGet(Ticket::new);
    }

    public List<Ticket> getAll() {
        return ticketRepo.getAll();
    }

    public List<Ticket> getUserTickets(User user, int pageSize, int pageNum){
        return ticketRepo.getUserTickets(user, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum){
        return ticketRepo.getBookedTickets(event, pageSize, pageNum);
    }

    public Ticket update(Ticket ticket) {
        return ticketRepo.update(ticket).orElseGet(Ticket::new);
    }

    public boolean cancelTicket(Long id) {
        return ticketRepo.deleteById(id);
    }
}
