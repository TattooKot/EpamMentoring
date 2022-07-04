package org.example.core.service;

import org.example.core.model.Event;
import org.example.core.repository.EventRepo;

import java.util.Map;

public class EventService {
    private final EventRepo eventRepo = new EventRepo();

    public Map.Entry<Integer, Event> save(Event event) {
        return eventRepo.save(event).orElseGet(() -> Map.entry(0, null));
    }

    public Map.Entry<Integer, Event> getById(Integer id) {
        return eventRepo.getById(id).orElseGet(() -> Map.entry(0, null));
    }

    public Map<Integer, Event> getAll() {
        return eventRepo.getAll();
    }

    public Map.Entry<Integer, Event> update(Event event) {
        return eventRepo.update(event).orElseGet(() -> Map.entry(0, null));
    }

    public void deleteById(Integer id) {
        eventRepo.deleteById(id);
    }
}
