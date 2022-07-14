package org.example.core.service;

import org.example.core.model.Event;
import org.example.core.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    public Event save(Event event) {
        return eventRepo.save(event).orElseGet(Event::new);
    }

    public Event getById(Long id) {
        return eventRepo.getById(id).orElseGet(Event::new);
    }

    public List<Event> getAll() {
        return eventRepo.getAll();
    }

    public Event update(Event event) {
        return eventRepo.update(event).orElseGet(Event::new);
    }

    public boolean deleteById(Long id) {
        return eventRepo.deleteById(id);
    }

    public List<Event> getByTitle(String title, int pageSize, int pageNum){
        return eventRepo.getByTitle(title, pageSize, pageNum);
    }

    public List<Event> getForDate(Date day, int pageSize, int pageNum){
        return eventRepo.getForDay(day, pageSize, pageNum);
    }
}
