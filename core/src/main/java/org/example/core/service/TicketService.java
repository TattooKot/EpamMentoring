package org.example.core.service;

import org.example.core.model.Ticket;
import org.example.core.repository.TicketRepo;

import java.util.Map;

public class TicketService {
    private final TicketRepo ticketRepo = new TicketRepo();

    public Map.Entry<Integer, Ticket> save(Ticket ticket) {
        return ticketRepo.save(ticket).orElseGet(() -> Map.entry(0, null));
    }

    public Map.Entry<Integer, Ticket> getById(Integer id) {
        return ticketRepo.getById(id).orElseGet(() -> Map.entry(0, null));
    }

    public Map<Integer, Ticket> getAll() {
        return ticketRepo.getAll();
    }

    public Map.Entry<Integer, Ticket> update(Ticket ticket) {
        return ticketRepo.update(ticket).orElseGet(() -> Map.entry(0, null));
    }

    public void deleteById(Integer id) {
        ticketRepo.deleteById(id);
    }
}
