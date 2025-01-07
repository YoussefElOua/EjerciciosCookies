package org.example.ejercicioscookies.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.ejercicioscookies.Entidades.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoockieController {

    @GetMapping("/")
    public String handleRequest(HttpSession session, Model model) {
        Counter counter = (Counter)
                session.getAttribute("counter");
        if (counter == null) {
            counter = new Counter();
        }
        counter.increment();
        session.setAttribute("counter", counter);
        model.addAttribute("count", counter.getCount());
        return "counter";
    }
}
