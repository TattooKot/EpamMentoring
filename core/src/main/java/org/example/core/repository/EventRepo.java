package org.example.core.repository;

import org.example.core.model.Event;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventRepo implements CRUDRepo<Integer, Event> {

    private final Map<Integer, Event> eventMap = new ConcurrentHashMap<>();

    @Override
    public Map.Entry<Integer, Event> save(Event event) {
        int newId = eventMap.size() + 1;
        eventMap.put(newId, event);
        return Map.entry(newId,event);
    }

    @Override
    public Map.Entry<Integer, Event> getById(Integer id) {
        return Map.entry(id, eventMap.get(id));
    }

    @Override
    public Map<Integer, Event> getAll() {
        return Collections.unmodifiableMap(eventMap);
    }

    @Override
    public Map.Entry<Integer, Event> update(Event event) {
        Integer id =
                eventMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(event))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        eventMap.put(id, event);
        return Map.entry(id, event);
    }

    @Override
    public void deleteById(Integer id) {
        eventMap.remove(id);
    }
}
