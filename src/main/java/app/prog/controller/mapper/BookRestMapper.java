package app.prog.controller.mapper;

import app.prog.controller.request.BookRequest;
import app.prog.controller.request.BookUpdateRequest;
import app.prog.controller.response.BookResponse;
import app.prog.model.Book;
import app.prog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookRestMapper {
    private final BookService bookService;
    public BookResponse toRest(Book domain) {
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(domain.getAuthor())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public Book toCreate(BookRequest data) {
        return Book.builder()
                .title(data.getTitle())
                .pageNumber(100)
                .author(data.getAuthor())
                .build();
    }

    public Book toUpdate(BookUpdateRequest data) {
        Book toUpdate = bookService.getBookById(data.getId());
             toUpdate.setAuthor(data.getAuthor());
             toUpdate.setTitle(data.getTitle());
        return toUpdate;
    }
}
