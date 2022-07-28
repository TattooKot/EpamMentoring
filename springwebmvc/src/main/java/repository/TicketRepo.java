package repository;

import org.springframework.beans.factory.annotation.Autowired;
import utils.DB;
import model.Event;
import model.Ticket;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TicketRepo implements CRUDRepo<Long, Ticket> {

    @Autowired
    private DB db;

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        long newId = db.ticketMap.size() + 1;
        ticket.setId(newId);
        db.ticketMap.put(newId, ticket);
        return Optional.of(ticket);
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        if(db.ticketMap.get(id) == null){
            return Optional.empty();
        }
        return Optional.of(db.ticketMap.get(id));
    }

    @Override
    public List<Ticket> getAll() {
        return db.ticketMap.values().stream().toList();
    }

    public List<Ticket> getUserTickets(User user, int pageSize, int pageNum){
        return db.ticketMap.values()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum){
        return db.ticketMap.values()
                .stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }

    @Override
    public Optional<Ticket> update(Ticket ticket) {
        Long id =
                db.ticketMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(ticket))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if(id == 0){
            return Optional.empty();
        }

        db.ticketMap.put(id, ticket);
        return Optional.of(ticket);
    }

    @Override
    public boolean deleteById(Long id) {
        return db.ticketMap.remove(id) != null;
    }
}
