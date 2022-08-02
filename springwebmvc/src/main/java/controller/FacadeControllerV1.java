package controller;

import facade.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacadeControllerV1 {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
