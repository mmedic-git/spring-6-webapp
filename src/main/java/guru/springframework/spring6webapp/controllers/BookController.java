package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BooksService booksService;

    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {

        model.addAttribute("books", booksService.findAll());

        return "books";
    }

}
