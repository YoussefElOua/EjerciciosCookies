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
        // Agregar un nuevo atributo vacío al modelo
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
            session.setAttribute("persona", persona);
        }
        model.addAttribute("person", persona.getName());
        return "login"; // Renderizar createName.html
    }

    @PostMapping("/name/save")
    public String saveName(@RequestParam("name") String name, HttpSession session) {
        // Obtener la instancia de Persona desde la sesión
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
        }

        // Actualizar el nombre
        persona.setName(name);
        session.setAttribute("persona", persona);

        // Redirigir a la página que muestra el nombre
        return "redirect:/name";
    }

    @GetMapping("/name")
    public String showName(HttpSession session, Model model) {
        // Obtener la persona desde la sesión
        Persona persona = (Persona) session.getAttribute("persona");

        // Validar si hay un nombre
        if (persona == null || persona.getName() == null || persona.getName().isEmpty()) {
            return "redirect:/name/create"; // Redirigir si no hay un nombre
        }

        // Agregar el nombre al modelo
        model.addAttribute("person", persona.getName());
        return "name"; // Renderizar showName.html
    }




}
