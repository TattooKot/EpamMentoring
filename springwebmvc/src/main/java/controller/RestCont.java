package controller;

import org.springframework.stereotype.Controller;
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
}
