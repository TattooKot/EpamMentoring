package controller;

import facade.BookingFacade;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class RestContV1 {

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

    @GetMapping("/user")
    public String user(Model model) throws IOException {
        User user = new User();
//        user.setEmail("email@epam.com");
//        user.setName("First user");
//        user.setPhone(123456789);
//        System.out.println(user);

        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String createUser(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) User user, Model model) {
        user = bookingFacade.createUser(user);
        model.addAttribute("user", user);
        return "user";
    }
}
