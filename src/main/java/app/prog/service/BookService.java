package app.prog.service;

import app.prog.model.BookEntity;
import app.prog.model.exception.NoSuchElementException;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public List<BookEntity> createBooks(List<BookEntity> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<BookEntity> updateBooks(List<BookEntity> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    public BookEntity deleteBook(int BookEntityId) {
        Optional<BookEntity> optional = repository.findById(String.valueOf(BookEntityId));
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return optional.get();
        } else {
            throw new NoSuchElementException("BookEntity." + BookEntityId + " not found");
        }
    }
}
