package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class EventRepo implements CRUDRepo<Long, Event> {

    private final Map<Long, Event> eventMap = DB.eventMap;

    @Override
    public Optional<Map.Entry<Long, Event>> save(Event event) {
        Long newId = (long) (eventMap.size() + 1);
        event.setId(newId);
        eventMap.put(newId, event);
        return Optional.of(Map.entry(newId,event));
    }

    @Override
    public Optional<Map.Entry<Long, Event>> getById(Long id) {
        if(eventMap.get(id) == null){
            return Optional.empty();
        }
        return Optional.of(Map.entry(id, eventMap.get(id)));
    }

    @Override
    public Map<Long, Event> getAll() {
        return eventMap;
    }

    @Override
    public Optional<Map.Entry<Long, Event>> update(Event event) {
        Long id =
                eventMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(event))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if(id == 0){
            return Optional.empty();
        }

        eventMap.put(id, event);
        return Optional.of(Map.entry(id, event));
    }

    @Override
    public void deleteById(Long id) {
        eventMap.remove(id);
    }
}
