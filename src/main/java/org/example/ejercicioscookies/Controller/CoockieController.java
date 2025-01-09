package org.example.ejercicioscookies.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.ejercicioscookies.Entidades.Counter;
import org.example.ejercicioscookies.Entidades.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/name/create")
    public String createNameForm(HttpSession session, Model model) {

        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
            session.setAttribute("persona", persona);
        }
        model.addAttribute("person", persona.getName());
        return "login";
    }

    @PostMapping("/name/save")
    public String saveName(@RequestParam("name") String name, HttpSession session) {

        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
        }


        persona.setName(name);
        session.setAttribute("persona", persona);


        return "redirect:/name";
    }

    @GetMapping("/name")
    public String showName(HttpSession session, Model model) {

        Persona persona = (Persona) session.getAttribute("persona");


        if (persona == null || persona.getName() == null || persona.getName().isEmpty()) {
            return "redirect:/name/create";
        }


        model.addAttribute("person", persona.getName());
        return "name";
    }




}
