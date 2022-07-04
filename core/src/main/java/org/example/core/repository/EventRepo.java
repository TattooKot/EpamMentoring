package org.example.core.repository;

import org.example.core.model.Event;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class EventRepo implements CRUDRepo<Integer, Event> {

    private final Map<Integer, Event> eventMap = new ConcurrentHashMap<>();

    @Override
    public Optional<Map.Entry<Integer, Event>> save(Event event) {
        int newId = eventMap.size() + 1;
        eventMap.put(newId, event);
        return Optional.of(Map.entry(newId,event));
    }

    @Override
    public Optional<Map.Entry<Integer, Event>> getById(Integer id) {
        if(eventMap.get(id) == null){
            return Optional.empty();
        }
        return Optional.of(Map.entry(id, eventMap.get(id)));
    }

    @Override
    public Map<Integer, Event> getAll() {
        return Collections.unmodifiableMap(eventMap);
    }

    @Override
    public Optional<Map.Entry<Integer, Event>> update(Event event) {
        Integer id =
                eventMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(event))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        if(id == 0){
            return Optional.empty();
        }

        eventMap.put(id, event);
        return Optional.of(Map.entry(id, event));
    }

    @Override
    public void deleteById(Integer id) {
        eventMap.remove(id);
    }
}
