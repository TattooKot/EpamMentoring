package utils;

import model.Event;
import model.Ticket;
import model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DB {
    public final Map<Long, Event> eventMap = new ConcurrentHashMap<>();
    public final Map<Long, Ticket> ticketMap = new ConcurrentHashMap<>();
    public final Map<Long, User> userMap = new ConcurrentHashMap<>();
}
