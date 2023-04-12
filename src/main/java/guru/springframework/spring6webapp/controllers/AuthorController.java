package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // iznimno je bitno da napraviš ovu anotaciju @Controller, bez toga neće raditi
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model) {

        model.addAttribute("authors", authorService.findAll()); //ovime unutar unutar modela kreiramo atribut authors, kome pridružujemo rezultat authorService.findAll()) metode . Unutar modela onda iteriramo kroz to

        return "authors";  //ovaj String mora odgovarati nazivu resource-a u model-view, dakle authors.html moraš napraviti u Resource folderu
    }
}
