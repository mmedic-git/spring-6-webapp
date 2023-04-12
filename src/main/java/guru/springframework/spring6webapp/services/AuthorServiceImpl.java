package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service // iznimno je bitno da napraviš ovu anotaciju @Service, bez toga neće raditi
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> findAll() {    //želimo implementirati findAll metodu na način da pozovemo ugrađenu findAll metodu "repozitorija" AuthorRepository
        return authorRepository.findAll();
    }
}
