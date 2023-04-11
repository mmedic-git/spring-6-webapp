package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BooksRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    /* Milan Medić: 4.4.2023 ova klasa će se pokretati kod svakog pokretanja SpringBoot-a, u kontekstu CommandLineRunner klase */

    private final AuthorRepository      authorRepository;
    private final BooksRepository       booksRepository;
    private final PublisherRepository   publisherRepository;

    /* ovaj constructor će se odraditi kod pokretanja */
    public BootstrapData(AuthorRepository authorRepository, BooksRepository booksRepository, PublisherRepository publisherRepository) {
        this.authorRepository       = authorRepository;
        this.booksRepository        = booksRepository;
        this.publisherRepository    = publisherRepository;
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

        Author ericSaved = authorRepository.save(eric); /* dobra je praksa vratiti novi objekt nakon snimanja u H2 bazu */
        Book dddSaved = booksRepository.save(ddd);


        System.out.println("BootstrapData starts ____________________________________________________________________________________________________________________________________");
        System.out.println("IN Bootstrap");

        Author  rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = booksRepository.save(noEJB);



        //perzistirajmo objekte
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);



        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        publisher.setCity("Split");
        publisher.setState("Croatia");
        publisher.setZipCode("21000");

        Publisher savedPublisher = publisherRepository.save(publisher);

        // dodajmo svakog knjizi njenog izdavača- publishera
        dddSaved.setPublisher(savedPublisher);
        noEJB.setPublisher(savedPublisher);

        authorRepository.save((ericSaved));
        authorRepository.save(rodSaved);

        booksRepository.save(dddSaved);
        booksRepository.save(noEJBSaved);








        System.out.println("Printam rod-a: " + rod);

        System.out.println("Printam noEJB: " + noEJB);


        System.out.println("Printam eric-a: " + eric);
        System.out.println("Printam ddd: " + ddd);

        System.out.println("Printam publisher: " + publisher);

        System.out.println("Author Count: "     + authorRepository.count());
        System.out.println("Book count: "       + booksRepository.count());
        System.out.println("Publisher Count: "  + publisherRepository.count());

        System.out.println("BootstrapData ends   ____________________________________________________________________________________________________________________________________");

    }
}
