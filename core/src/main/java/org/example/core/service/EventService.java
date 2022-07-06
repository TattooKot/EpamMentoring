package org.example.core.service;

import org.example.core.model.Event;
import org.example.core.repository.EventRepo;

import java.util.Map;

public class EventService {
    private final EventRepo eventRepo = new EventRepo();

    public Map.Entry<Long, Event> save(Event event) {
        return eventRepo.save(event).orElseGet(() -> Map.entry(0L, new Event()));
    }

    public Map.Entry<Long, Event> getById(Long id) {
        return eventRepo.getById(id).orElseGet(() -> Map.entry(0L, new Event()));
    }

    public Map<Long, Event> getAll() {
        return eventRepo.getAll();
    }

    public Map.Entry<Long, Event> update(Event event) {
        return eventRepo.update(event).orElseGet(() -> Map.entry(0L, new Event()));
    }

    public void deleteById(Long id) {
        eventRepo.deleteById(id);
    }
}
