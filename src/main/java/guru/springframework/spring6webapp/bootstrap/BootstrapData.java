package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BooksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    /* Milan Medić: 4.4.2023 ova klasa će se pokretati kod svakog pokretanja SpringBoot-a, u kontekstu CommandLineRunner klase */

    private final AuthorRepository authorRepository;
    private final BooksRepository booksRepository;

    /* ovaj constructor će se odraditi kod pokretanja */
    public BootstrapData(AuthorRepository authorRepository, BooksRepository booksRepository) {
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    // moramo override-ati run metodu da provajdamo kod koji će se izvršiti kod pokretanja SpringBoot-a. Originalni run je abstract, tako da
    // moram isporučiti konkretan kod koji će se izvršiti
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric); /* dobra je praksa vratiti novi objekt nakon snimanja "u bazu" */
        Book dddSaved = booksRepository.save(ddd);


        System.out.println("BootstrapData starts ____________________________________________________________________________________________________________________________________");

        Author  rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = booksRepository.save(noEJB);




        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        //perzistirajmo objekte

        authorRepository.save((ericSaved));
        authorRepository.save(rodSaved);

        System.out.println("IN Bootstrap");

        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book count: " + booksRepository.count());

        System.out.println("Printam rod-a: " + rod);
        System.out.println("Printam noEJB: " + noEJB);

        System.out.println("Printam eric-a: " + eric);
        System.out.println("Printam ddd: " + ddd);


        System.out.println("BootstrapData ends   ____________________________________________________________________________________________________________________________________");

    }
}
