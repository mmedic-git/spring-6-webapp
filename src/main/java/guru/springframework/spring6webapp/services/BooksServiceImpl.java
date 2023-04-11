package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.BooksRepository;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return booksRepository.findAll();
    }
}
