package controller;

import facade.BookingFacade;
import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventControllerV1 {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/eventNavigation")
    public String eventNavigation() {
        return "eventNavigation";
    }

    @GetMapping("/eventSearch")
    public String eventSearch() {
        return "eventSearch";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle() {
        return "eventSearchByTitle";
    }

    @GetMapping("/searchByDate")
    public String searchByDate() {
        return "eventSearchByDate";
    }

    @GetMapping("/addEvent")
    public String getEventForm() {
        return "eventForm";
    }

    @PostMapping("/event")
    public String createEvent(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Event event, Model model) {
        event = bookingFacade.createEvent(event);
        model.addAttribute("event", event);
        return "event";
    }

    @GetMapping("/event/{id}")
    public String eventById(Model model, @PathVariable Integer id) {
        Event event = bookingFacade.getEventById(id);
        model.addAttribute("event", event);
        return "event";
    }
}
