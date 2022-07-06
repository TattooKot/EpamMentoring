package org.example.core;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    public static final Map<Long, Event> eventMap = new ConcurrentHashMap<>();
    public static final Map<Long, Ticket> ticketMap = new ConcurrentHashMap<>();
    public static final Map<Long, User> userMap = new ConcurrentHashMap<>();
}
