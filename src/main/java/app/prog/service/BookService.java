package app.prog.service;

import app.prog.model.Book;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public List<Book> createBooks(List<Book> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<Book> updateBooks(List<Book> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    public Book getBookById(int id) {
        return repository.getById(id);
    }

    public ResponseEntity<Book> deleteBook(int bookId) {
        Optional<Book> optional = repository.findById(bookId);
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return ResponseEntity.ok().body(optional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book.id=" + bookId + " not found");
        }
    }
}
