package org.example.core.service;

import org.example.core.model.Ticket;
import org.example.core.repository.TicketRepo;

import java.util.List;

public class TicketService {
    private final TicketRepo ticketRepo = new TicketRepo();

    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket).orElseGet(Ticket::new);
    }

    public Ticket getById(Long id) {
        return ticketRepo.getById(id).orElseGet(Ticket::new);
    }

    public List<Ticket> getAll() {
        return ticketRepo.getAll();
    }

    public Ticket update(Ticket ticket) {
        return ticketRepo.update(ticket).orElseGet(Ticket::new);
    }

    public boolean deleteById(Long id) {
        return ticketRepo.deleteById(id);
    }
}
