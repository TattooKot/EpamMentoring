package org.example.core;

import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    public static final Map<Integer, Event> eventMap = new ConcurrentHashMap<>();
    public static final Map<Integer, Ticket> ticketMap = new ConcurrentHashMap<>();
    public static final Map<Integer, User> userMap = new ConcurrentHashMap<>();
}
