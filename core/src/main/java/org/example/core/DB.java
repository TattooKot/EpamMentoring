package org.example.core;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DB {
    public final Map<Long, Event> eventMap = new ConcurrentHashMap<>();
    public final Map<Long, Ticket> ticketMap = new ConcurrentHashMap<>();
    public final Map<Long, User> userMap = new ConcurrentHashMap<>();
}
