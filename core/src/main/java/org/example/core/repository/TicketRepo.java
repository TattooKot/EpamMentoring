package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.Ticket;

import java.util.Map;
import java.util.Optional;

public class TicketRepo implements CRUDRepo<Integer, Ticket> {

    private final Map<Integer, Ticket> ticketMap = DB.ticketMap;

    @Override
    public Optional<Map.Entry<Integer, Ticket>> save(Ticket ticket) {
        int newId = ticketMap.size() + 1;
        ticket.setId(newId);
        ticketMap.put(newId, ticket);
        return Optional.of(Map.entry(newId, ticket));
    }

    @Override
    public Optional<Map.Entry<Integer, Ticket>> getById(Integer id) {
        if(ticketMap.get(id) == null){
            return Optional.empty();
        }
        return Optional.of(Map.entry(id, ticketMap.get(id)));
    }

    @Override
    public Map<Integer, Ticket> getAll() {
        return ticketMap;
    }

    @Override
    public Optional<Map.Entry<Integer, Ticket>> update(Ticket ticket) {
        Integer id =
                ticketMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(ticket))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        if(id == 0){
            return Optional.empty();
        }

        ticketMap.put(id, ticket);
        return Optional.of(Map.entry(id, ticket));
    }

    @Override
    public void deleteById(Integer id) {
        ticketMap.remove(id);
    }
}
