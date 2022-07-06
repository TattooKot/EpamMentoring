package org.example.core.service;

import org.example.core.model.Ticket;
import org.example.core.repository.TicketRepo;

import java.util.Map;

public class TicketService {
    private final TicketRepo ticketRepo = new TicketRepo();

    public Map.Entry<Long, Ticket> save(Ticket ticket) {
        return ticketRepo.save(ticket).orElseGet(() -> Map.entry(0L, new Ticket()));
    }

    public Map.Entry<Long, Ticket> getById(Long id) {
        return ticketRepo.getById(id).orElseGet(() -> Map.entry(0L, new Ticket()));
    }

    public Map<Long, Ticket> getAll() {
        return ticketRepo.getAll();
    }

    public Map.Entry<Long, Ticket> update(Ticket ticket) {
        return ticketRepo.update(ticket).orElseGet(() -> Map.entry(0L, new Ticket()));
    }

    public void deleteById(Long id) {
        ticketRepo.deleteById(id);
    }
}
