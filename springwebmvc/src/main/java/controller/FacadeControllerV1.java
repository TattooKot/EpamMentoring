package controller;

import facade.BookingFacade;
import model.Event;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class FacadeControllerV1 {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/addUser")
    public String getAccountForm() {
        return "userForm";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable Integer id) throws IOException {
        User user = bookingFacade.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String createUser(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) User user, Model model) {
        user = bookingFacade.createUser(user);
        model.addAttribute("user", user);
        return "user";
    }


    @GetMapping("/addEvent")
    public String getEventForm() {
        return "eventForm";
    }

    @GetMapping("/event/{id}")
    public String event(Model model, @PathVariable Integer id) throws IOException {
        Event event = bookingFacade.getEventById(id);
        model.addAttribute("event", event);
        return "user";
    }

    @PostMapping("/event")
    public String createEvent(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Event event, Model model) {
        event = bookingFacade.createEvent(event);
        System.out.println(event);
        model.addAttribute("event", event);
        return "event";
    }
}
