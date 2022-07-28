package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class RestCont {

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("{\"Hello world from events!\"}");
    }

    @GetMapping("/user")
    public String user(Model model) throws IOException {
        User user = new User();
        user.setEmail("email@epam.com");
        user.setName("First user");
        user.setPhone(123456789);
        System.out.println(user);

        model.addAttribute("user", user);
        return "user";
    }
}
