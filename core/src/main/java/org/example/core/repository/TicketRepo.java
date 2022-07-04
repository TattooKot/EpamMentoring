package org.example.core.repository;

import org.example.core.model.Ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRepo implements CRUDRepo<Integer, Ticket> {

    private final Map<Integer, Ticket> ticketMap = new ConcurrentHashMap<>();

    @Override
    public Map.Entry<Integer, Ticket> save(Ticket ticket) {
        int newId = ticketMap.size() + 1;
        ticketMap.put(newId, ticket);
        return Map.entry(newId, ticket);
    }

    @Override
    public Map.Entry<Integer, Ticket> getById(Integer id) {
        return Map.entry(id, ticketMap.get(id));
    }

    @Override
    public Map<Integer, Ticket> getAll() {
        return ticketMap;
    }

    @Override
    public Map.Entry<Integer, Ticket> update(Ticket ticket) {
        Integer id =
                ticketMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(ticket))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        ticketMap.put(id, ticket);
        return Map.entry(id, ticket);
    }

    @Override
    public void deleteById(Integer id) {
        ticketMap.remove(id);
    }
}
