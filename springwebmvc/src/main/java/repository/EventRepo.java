package repository;

import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.DB;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class EventRepo implements CRUDRepo<Long, Event> {

    @Autowired
    private DB db;

    @Override
    public Optional<Event> save(Event event) {
        long newId = db.eventMap.size() + 1;
        event.setId(newId);
        db.eventMap.put(newId, event);
        return Optional.of(event);
    }

    @Override
    public Optional<Event> getById(Long id) {
        if (db.eventMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(db.eventMap.get(id));
    }

    @Override
    public List<Event> getAll() {
        return db.eventMap.values().stream().toList();
    }

    @Override
    public Optional<Event> update(Event event) {
        Long id =
                db.eventMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(event))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if (id == 0) {
            return Optional.empty();
        }

        db.eventMap.put(id, event);
        return Optional.of(event);
    }

    @Override
    public boolean deleteById(Long id) {
        return db.eventMap.remove(id) != null;
    }


    public List<Event> getByTitle(String title, int pageSize, int pageNum){
        return db.eventMap.values()
                .stream()
                .filter(event -> event.getTitle().contains(title))
                .skip((long) pageNum * pageNum)
                .limit(pageSize)
                .toList();
    }

    public List<Event> getForDay(Date day, int pageSize, int pageNum){
        long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
        Date startDate = new Date(day.getTime()-DAY_IN_MILLIS);
        Date endDate = new Date(day.getTime()+DAY_IN_MILLIS);

        return db.eventMap
                .values()
                .stream()
                .filter(event ->
                        event.getDate().after(startDate)
                        && event.getDate().before(endDate))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }
}
