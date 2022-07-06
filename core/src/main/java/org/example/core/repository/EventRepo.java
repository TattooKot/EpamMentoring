package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.Event;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EventRepo implements CRUDRepo<Long, Event> {

    private final Map<Long, Event> eventMap = DB.eventMap;

    @Override
    public Optional<Event> save(Event event) {
        long newId = eventMap.size() + 1;
        event.setId(newId);
        eventMap.put(newId, event);
        return Optional.of(event);
    }

    @Override
    public Optional<Event> getById(Long id) {
        if (eventMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(eventMap.get(id));
    }

    @Override
    public List<Event> getAll() {
        return eventMap.values().stream().toList();
    }

    @Override
    public Optional<Event> update(Event event) {
        Long id =
                eventMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(event))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if (id == 0) {
            return Optional.empty();
        }

        eventMap.put(id, event);
        return Optional.of(event);
    }

    @Override
    public void deleteById(Long id) {
        eventMap.remove(id);
    }
}
